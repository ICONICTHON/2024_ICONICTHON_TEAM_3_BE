package com.akofood.server.service;

import com.akofood.server.dto.req.RestaurantRequest;
import com.akofood.server.dto.res.MenuItemResponse;
import com.akofood.server.dto.res.RestaurantResponse;
import com.akofood.server.entity.Restaurant;
import com.akofood.server.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<RestaurantResponse> getRestaurantById(Long id) {
        return restaurantRepository.findById(id).map(this::convertToResponse);
    }

    public RestaurantResponse createRestaurant(RestaurantRequest request) {
        Restaurant restaurant = convertToEntity(request);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return convertToResponse(savedRestaurant);
    }

    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setAddress(request.getAddress());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setWeekdayHours(request.getWeekdayHours());
        restaurant.setWeekendHours(request.getWeekendHours());
        restaurant.setHolidayHours(request.getHolidayHours());

        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return convertToResponse(updatedRestaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }


    private RestaurantResponse convertToResponse(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setRestaurantName(restaurant.getRestaurantName());
        response.setAddress(restaurant.getAddress());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setWeekdayHours(restaurant.getWeekdayHours());
        response.setWeekendHours(restaurant.getWeekendHours());
        response.setHolidayHours(restaurant.getHolidayHours());
        response.setCreatedAt(restaurant.getCreatedAt());
        response.setUpdatedAt(restaurant.getUpdatedAt());

        // MenuItems를 MenuItemResponse로 변환하여 설정
        List<MenuItemResponse> menuItems = restaurant.getMenuItems().stream()
                .map(menuItem -> {
                    MenuItemResponse itemResponse = new MenuItemResponse();
                    itemResponse.setId(menuItem.getId());
                    itemResponse.setRestaurantId(restaurant.getId());  // restaurantId 설정
                    itemResponse.setMenuName(menuItem.getMenuName());
                    itemResponse.setMenuPrice(menuItem.getMenuPrice());
                    itemResponse.setDailyUsageLimit(menuItem.getDailyUsageLimit());
                    itemResponse.setDailyUsageCount(menuItem.getDailyUsageCount());
                    itemResponse.setTotalUsageCount(menuItem.getTotalUsageCount());
                    itemResponse.setDailyVoucherSales(menuItem.getDailyVoucherSales());
                    itemResponse.setTotalVoucherSales(menuItem.getTotalVoucherSales());
                    itemResponse.setLikedCount(menuItem.getLikedCount());
                    itemResponse.setCreatedAt(menuItem.getCreatedAt());
                    itemResponse.setUpdatedAt(menuItem.getUpdatedAt());
                    return itemResponse;
                })
                .collect(Collectors.toList());

        response.setMenuItems(menuItems);

        return response;
    }

    private RestaurantResponse convertToResponse(Restaurant restaurant, String menuName) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setRestaurantName(restaurant.getRestaurantName());
        response.setAddress(restaurant.getAddress());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setWeekdayHours(restaurant.getWeekdayHours());
        response.setWeekendHours(restaurant.getWeekendHours());
        response.setHolidayHours(restaurant.getHolidayHours());
        response.setCreatedAt(restaurant.getCreatedAt());
        response.setUpdatedAt(restaurant.getUpdatedAt());

        // menuName 조건을 만족하는 MenuItems만 필터링하여 설정
        List<MenuItemResponse> menuItems = restaurant.getMenuItems().stream()
                .filter(menuItem -> menuItem.getMenuName().toLowerCase().contains(menuName.toLowerCase()))
                .map(menuItem -> {
                    MenuItemResponse itemResponse = new MenuItemResponse();
                    itemResponse.setId(menuItem.getId());
                    itemResponse.setRestaurantId(restaurant.getId());
                    itemResponse.setMenuName(menuItem.getMenuName());
                    itemResponse.setMenuPrice(menuItem.getMenuPrice());
                    itemResponse.setDailyUsageLimit(menuItem.getDailyUsageLimit());
                    itemResponse.setDailyUsageCount(menuItem.getDailyUsageCount());
                    itemResponse.setTotalUsageCount(menuItem.getTotalUsageCount());
                    itemResponse.setDailyVoucherSales(menuItem.getDailyVoucherSales());
                    itemResponse.setTotalVoucherSales(menuItem.getTotalVoucherSales());
                    itemResponse.setLikedCount(menuItem.getLikedCount());
                    itemResponse.setCreatedAt(menuItem.getCreatedAt());
                    itemResponse.setUpdatedAt(menuItem.getUpdatedAt());
                    return itemResponse;
                })
                .collect(Collectors.toList());

        response.setMenuItems(menuItems);
        return response;
    }

    private Restaurant convertToEntity(RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setAddress(request.getAddress());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setWeekdayHours(request.getWeekdayHours());
        restaurant.setWeekendHours(request.getWeekendHours());
        restaurant.setHolidayHours(request.getHolidayHours());
        return restaurant;
    }

    public List<RestaurantResponse> getRestaurantsByMenuName(String menuName) {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> convertToResponse(restaurant, menuName))  // menuName을 인자로 전달
                .collect(Collectors.toList());
    }

}