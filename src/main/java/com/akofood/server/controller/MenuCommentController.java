package com.akofood.server.controller;

import com.akofood.server.dto.req.MenuCommentRequest;
import com.akofood.server.dto.res.MenuCommentResponse;
import com.akofood.server.entity.MenuComment;
import com.akofood.server.service.MenuCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-comments")
public class MenuCommentController {

    @Autowired
    private MenuCommentService menuCommentService;

    @GetMapping
    public List<MenuCommentResponse> getAllMenuComments() {
        return menuCommentService.getAllMenuComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuCommentResponse> getMenuCommentById(@PathVariable Long id) {
        return menuCommentService.getMenuCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MenuCommentResponse createMenuComment(@RequestBody MenuCommentRequest request) {
        return menuCommentService.createMenuComment(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuCommentResponse> updateMenuComment(@PathVariable Long id, @RequestBody MenuCommentRequest request) {
        return ResponseEntity.ok(menuCommentService.updateMenuComment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuComment(@PathVariable Long id) {
        menuCommentService.deleteMenuComment(id);
        return ResponseEntity.noContent().build();
    }
}