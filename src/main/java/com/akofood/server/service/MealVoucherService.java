package com.akofood.server.service;

import com.akofood.server.dto.req.MealVoucherRequest;
import com.akofood.server.dto.res.MealVoucherResponse;
import com.akofood.server.entity.MealVoucher;
import com.akofood.server.entity.MenuItem;
import com.akofood.server.entity.Restaurant;
import com.akofood.server.entity.User;
import com.akofood.server.repository.MealVoucherRepository;
import com.akofood.server.repository.MenuItemRepository;
import com.akofood.server.repository.RestaurantRepository;
import com.akofood.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MealVoucherService {

    @Autowired
    private MealVoucherRepository mealVoucherRepository;

    // User, Restaurant, MenuItem
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;


    public List<MealVoucherResponse> getAllMealVouchers() {
        return mealVoucherRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<MealVoucherResponse> getMealVoucherById(Long id) {
        return mealVoucherRepository.findById(id).map(this::convertToResponse);
    }

    public MealVoucherResponse createMealVoucher(MealVoucherRequest request) {
        MealVoucher mealVoucher = convertToEntity(request);
        MealVoucher savedVoucher = mealVoucherRepository.save(mealVoucher);
        return convertToResponse(savedVoucher);
    }

    public MealVoucherResponse updateMealVoucher(Long id, MealVoucherRequest request) {
        MealVoucher mealVoucher = mealVoucherRepository.findById(id).orElseThrow();
        mealVoucher.setUniqueIdentifier(request.getUniqueIdentifier());
        mealVoucher.setOrderNumber(request.getOrderNumber());
        mealVoucher.setUsed(request.isUsed());
        MealVoucher updatedVoucher = mealVoucherRepository.save(mealVoucher);
        return convertToResponse(updatedVoucher);
    }

    public void deleteMealVoucher(Long id) {
        mealVoucherRepository.deleteById(id);
    }

    private MealVoucherResponse convertToResponse(MealVoucher mealVoucher) {
        MealVoucherResponse response = new MealVoucherResponse();
        response.setId(mealVoucher.getId());
        response.setUserId(mealVoucher.getUser().getId());
        response.setRestaurantId(mealVoucher.getRestaurant().getId());
        response.setMenuItemId(mealVoucher.getMenuItem().getId());
        response.setUniqueIdentifier(mealVoucher.getUniqueIdentifier());
        response.setOrderNumber(mealVoucher.getOrderNumber());
        response.setUsed(mealVoucher.isUsed());
        response.setCreatedAt(mealVoucher.getCreatedAt());
        response.setUpdatedAt(mealVoucher.getUpdatedAt());
        return response;
    }

    private MealVoucher convertToEntity(MealVoucherRequest request) {
        MealVoucher mealVoucher = new MealVoucher();

        // User, Restaurant, MenuItem
        User user = userRepository.findById(request.getUserId()).orElseThrow(NoSuchElementException::new);
        mealVoucher.setUser(user);
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow(NoSuchElementException::new);
        mealVoucher.setRestaurant(restaurant);
        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId()).orElseThrow(NoSuchElementException::new);
        mealVoucher.setMenuItem(menuItem);

        mealVoucher.setUniqueIdentifier(request.getUniqueIdentifier());
        mealVoucher.setOrderNumber(request.getOrderNumber());
        mealVoucher.setUsed(request.isUsed());
        return mealVoucher;
    }
}