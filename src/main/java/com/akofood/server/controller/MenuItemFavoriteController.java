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

    @GetMapping("/users/{user_id}/menu-items/{menu_item_id}/")
    public ResponseEntity<Boolean> getIsExistMenuItemFavorite(@PathVariable Long user_id, @PathVariable Long menu_item_id){
        Boolean isExist = menuItemFavoriteService.getIsExistMenuItemFavorite(user_id, menu_item_id);
        return ResponseEntity.ok(isExist); // Boolean을 ResponseEntity로 감싸서 반환
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