package com.akofood.server.service;

import com.akofood.server.dto.req.MenuCommentRequest;
import com.akofood.server.dto.res.MenuCommentResponse;
import com.akofood.server.entity.MenuComment;
import com.akofood.server.entity.MenuItem;
import com.akofood.server.entity.Restaurant;
import com.akofood.server.entity.User;
import com.akofood.server.repository.MenuCommentRepository;
import com.akofood.server.repository.MenuItemRepository;
import com.akofood.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuCommentService {

    @Autowired
    private MenuCommentRepository menuCommentRepository;

    // User, MenuItem
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuCommentResponse> getAllMenuComments() {
        return menuCommentRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<MenuCommentResponse> getMenuCommentById(Long id) {
        return menuCommentRepository.findById(id).map(this::convertToResponse);
    }

    public MenuCommentResponse createMenuComment(MenuCommentRequest request) {
        MenuComment menuComment = convertToEntity(request);
        MenuComment savedComment = menuCommentRepository.save(menuComment);
        return convertToResponse(savedComment);
    }

    public MenuCommentResponse updateMenuComment(Long id, MenuCommentRequest request) {
        MenuComment menuComment = menuCommentRepository.findById(id).orElseThrow();
        menuComment.setCommentContent(request.getCommentContent());
        MenuComment updatedComment = menuCommentRepository.save(menuComment);
        return convertToResponse(updatedComment);
    }

    public void deleteMenuComment(Long id) {
        menuCommentRepository.deleteById(id);
    }

    private MenuCommentResponse convertToResponse(MenuComment menuComment) {
        MenuCommentResponse response = new MenuCommentResponse();
        response.setId(menuComment.getId());
        response.setUserId(menuComment.getUser().getId());
        response.setMenuItemId(menuComment.getMenuItem().getId());
        response.setCommentContent(menuComment.getCommentContent());
        response.setCreatedAt(menuComment.getCreatedAt());
        response.setUpdatedAt(menuComment.getUpdatedAt());
        return response;
    }

    private MenuComment convertToEntity(MenuCommentRequest request) {
        MenuComment menuComment = new MenuComment();

        // User, MenuItem
        User user = userRepository.findById(request.getUserId()).orElseThrow(NoSuchElementException::new);
        menuComment.setUser(user);
        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId()).orElseThrow(NoSuchElementException::new);
        menuComment.setMenuItem(menuItem);

        menuComment.setCommentContent(request.getCommentContent());
        return menuComment;
    }
}