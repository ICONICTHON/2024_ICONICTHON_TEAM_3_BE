package com.akofood.server.controller;

import com.akofood.server.dto.req.RestaurantRequest;
import com.akofood.server.dto.res.RestaurantResponse;
import com.akofood.server.entity.Restaurant;
import com.akofood.server.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantResponse> getAllRestaurants(
            @RequestParam(value = "search_menu", required = false) String searchMenu) {
        if (searchMenu != null && !searchMenu.isEmpty()) {
            return restaurantService.getRestaurantsByMenuName(searchMenu);
        }
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //
    @GetMapping("/users/{user_id}/menu-item-favorites/")
    public List<RestaurantResponse> getRestaurantByMenuItemFavoritesAndByUserId(@PathVariable Long user_id) {
        return restaurantService.getFavoriteRestaurantsByUserId(user_id);
    }

    @PostMapping
    public RestaurantResponse createRestaurant(@RequestBody RestaurantRequest request) {
        return restaurantService.createRestaurant(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequest request) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}