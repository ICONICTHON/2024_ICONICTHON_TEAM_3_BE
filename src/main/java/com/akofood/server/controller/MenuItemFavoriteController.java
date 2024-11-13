package com.akofood.server.controller;

import com.akofood.server.dto.req.MenuItemFavoriteRequest;
import com.akofood.server.dto.res.MenuItemFavoriteResponse;
import com.akofood.server.entity.MenuItemFavorite;
import com.akofood.server.service.MenuItemFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-item-favorites")
public class MenuItemFavoriteController {

    @Autowired
    private MenuItemFavoriteService menuItemFavoriteService;

    @GetMapping
    public List<MenuItemFavoriteResponse> getAllMenuItemFavorites() {
        return menuItemFavoriteService.getAllMenuItemFavorites();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemFavoriteResponse> getMenuItemFavoriteById(@PathVariable Long id) {
        return menuItemFavoriteService.getMenuItemFavoriteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MenuItemFavoriteResponse createMenuItemFavorite(@RequestBody MenuItemFavoriteRequest request) {
        return menuItemFavoriteService.createMenuItemFavorite(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItemFavorite(@PathVariable Long id) {
        menuItemFavoriteService.deleteMenuItemFavorite(id);
        return ResponseEntity.noContent().build();
    }
}