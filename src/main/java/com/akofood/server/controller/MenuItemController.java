package com.akofood.server.controller;

import com.akofood.server.dto.req.MenuItemRequest;
import com.akofood.server.dto.res.MenuItemResponse;
import com.akofood.server.entity.MenuItem;
import com.akofood.server.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponse> getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MenuItemResponse createMenuItem(@RequestBody MenuItemRequest request) {
        return menuItemService.createMenuItem(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable Long id, @RequestBody MenuItemRequest request) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, request));
    }

    @PutMapping("/{id}/like")
    public ResponseEntity<MenuItemResponse> updateMenuItemLike(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.updateMenuItemLike(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}