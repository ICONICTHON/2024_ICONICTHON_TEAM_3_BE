package com.akofood.server.service;

import com.akofood.server.dto.req.MealVoucherCreateRequest;
import com.akofood.server.dto.req.MealVoucherRequest;
import com.akofood.server.dto.res.MealVoucherDetailResponse;
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

    public MealVoucherResponse createMealVoucher(MealVoucherCreateRequest request) {
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

    private MealVoucher convertToEntity(MealVoucherCreateRequest request) {
        MealVoucher mealVoucher = new MealVoucher();

        // User, Restaurant, MenuItem
        User user = userRepository.findById(request.getUserId()).orElseThrow(NoSuchElementException::new);
        mealVoucher.setUser(user);
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow(NoSuchElementException::new);
        mealVoucher.setRestaurant(restaurant);
        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId()).orElseThrow(NoSuchElementException::new);
        mealVoucher.setMenuItem(menuItem);

        // 메뉴 아이템 ID와 사용 여부를 기준으로 최근 사용된 orderNumber 가져오기
        Long menuItemId = request.getMenuItemId();
        Optional<MealVoucher> recentUsedVoucher = mealVoucherRepository.findTopByMenuItemIdAndUsedTrueOrderByUpdatedAtDesc(menuItemId);

        int orderNumber = recentUsedVoucher.map(voucher -> voucher.getOrderNumber() + 1).orElse(200);
        mealVoucher.setOrderNumber(orderNumber);

        mealVoucher.setUsed(false);
        return mealVoucher;
    }

    public List<MealVoucherDetailResponse> getMealVouchersByUserId(Long userId) {
        List<MealVoucher> mealVouchers = mealVoucherRepository.findByUserId(userId);

        return mealVouchers.stream().map(mealVoucher -> {
            MealVoucherDetailResponse response = new MealVoucherDetailResponse();
            response.setId(mealVoucher.getId());
            response.setUniqueIdentifier(mealVoucher.getUniqueIdentifier());
            response.setOrderNumber(mealVoucher.getOrderNumber());
            response.setUsed(mealVoucher.isUsed());

            // Restaurant ID 및 이름 설정
            Restaurant restaurant = mealVoucher.getRestaurant();
            response.setRestaurantId(restaurant.getId());
            response.setRestaurantName(restaurant.getRestaurantName());

            // MenuItem ID 및 정보 설정
            MenuItem menuItem = mealVoucher.getMenuItem();
            response.setMenuItemId(menuItem.getId());
            response.setMenuName(menuItem.getMenuName());
            response.setMenuPrice(menuItem.getMenuPrice());
            response.setOperatingHours(menuItem.getOperatingHours());
            response.setDailyUsageLimit(menuItem.getDailyUsageLimit());
            response.setDailyUsageCount(menuItem.getDailyUsageCount());
            response.setTotalUsageCount(menuItem.getTotalUsageCount());
            response.setDailyVoucherSales(menuItem.getDailyVoucherSales());
            response.setTotalVoucherSales(menuItem.getTotalVoucherSales());
            response.setLikedCount(menuItem.getLikedCount());

            return response;
        }).collect(Collectors.toList());
    }

}