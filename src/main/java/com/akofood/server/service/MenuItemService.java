package com.akofood.server.service;

import com.akofood.server.dto.req.MenuItemRequest;
import com.akofood.server.dto.res.MenuItemResponse;
import com.akofood.server.entity.MenuItem;
import com.akofood.server.entity.Restaurant;
import com.akofood.server.entity.User;
import com.akofood.server.repository.MenuItemRepository;
import com.akofood.server.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    // Restaurant
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<MenuItemResponse> getMenuItemById(Long id) {
        return menuItemRepository.findById(id).map(this::convertToResponse);
    }

    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        MenuItem menuItem = convertToEntity(request);
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);
        return convertToResponse(savedMenuItem);
    }

    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow();
        menuItem.setMenuName(request.getMenuName());
        menuItem.setMenuPrice(request.getMenuPrice());
        menuItem.setOperatingHours(request.getOperatingHours());
        menuItem.setDailyUsageLimit(request.getDailyUsageLimit());
        menuItem.setDailyUsageCount(request.getDailyUsageCount());
        menuItem.setTotalUsageCount(request.getTotalUsageCount());
        menuItem.setDailyVoucherSales(request.getDailyVoucherSales());
        menuItem.setTotalVoucherSales(request.getTotalVoucherSales());
        menuItem.setLikedCount(request.getLikedCount());
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        return convertToResponse(updatedMenuItem);
    }

    public MenuItemResponse updateMenuItemLike(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow();
        menuItem.setLikedCount(menuItem.getLikedCount()+1);
        MenuItem updatedMenuItem = menuItemRepository.save(menuItem);
        return convertToResponse(updatedMenuItem);
    }


    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    private MenuItemResponse convertToResponse(MenuItem menuItem) {
        MenuItemResponse response = new MenuItemResponse();
        response.setId(menuItem.getId());
        response.setRestaurantId(menuItem.getRestaurant().getId());
        response.setMenuName(menuItem.getMenuName());
        response.setMenuPrice(menuItem.getMenuPrice());
        response.setOperatingHours(menuItem.getOperatingHours());
        response.setDailyUsageLimit(menuItem.getDailyUsageLimit());
        response.setDailyUsageCount(menuItem.getDailyUsageCount());
        response.setTotalUsageCount(menuItem.getTotalUsageCount());
        response.setDailyVoucherSales(menuItem.getDailyVoucherSales());
        response.setTotalVoucherSales(menuItem.getTotalVoucherSales());
        response.setLikedCount(menuItem.getLikedCount());
        response.setCreatedAt(menuItem.getCreatedAt());
        response.setUpdatedAt(menuItem.getUpdatedAt());
        return response;
    }

    private MenuItem convertToEntity(MenuItemRequest request) {
        MenuItem menuItem = new MenuItem();

        // Restaurant
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow(NoSuchElementException::new);
        menuItem.setRestaurant(restaurant);

        menuItem.setMenuName(request.getMenuName());
        menuItem.setMenuPrice(request.getMenuPrice());
        menuItem.setOperatingHours(request.getOperatingHours());
        menuItem.setDailyUsageLimit(request.getDailyUsageLimit());
        menuItem.setDailyUsageCount(request.getDailyUsageCount());
        menuItem.setTotalUsageCount(request.getTotalUsageCount());
        menuItem.setDailyVoucherSales(request.getDailyVoucherSales());
        menuItem.setTotalVoucherSales(request.getTotalVoucherSales());
        menuItem.setLikedCount(request.getLikedCount());
        return menuItem;
    }
}