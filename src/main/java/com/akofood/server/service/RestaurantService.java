package com.akofood.server.service;

import com.akofood.server.dto.req.RestaurantRequest;
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
}