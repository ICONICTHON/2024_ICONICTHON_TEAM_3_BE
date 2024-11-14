package com.akofood.server.service;

import com.akofood.server.dto.req.MenuItemFavoriteRequest;
import com.akofood.server.dto.res.MenuItemFavoriteResponse;
import com.akofood.server.entity.MenuItem;
import com.akofood.server.entity.MenuItemFavorite;
import com.akofood.server.entity.User;
import com.akofood.server.repository.MenuItemFavoriteRepository;
import com.akofood.server.repository.MenuItemRepository;
import com.akofood.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuItemFavoriteService {

    @Autowired
    private MenuItemFavoriteRepository menuItemFavoriteRepository;

    // User, MenuItem
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItemFavoriteResponse> getAllMenuItemFavorites() {
        return menuItemFavoriteRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Optional<MenuItemFavoriteResponse> getMenuItemFavoriteById(Long id) {
        return menuItemFavoriteRepository.findById(id).map(this::convertToResponse);
    }

    public MenuItemFavoriteResponse createMenuItemFavorite(MenuItemFavoriteRequest request) {
        // 중복 여부 확인
        Optional<MenuItemFavorite> existingFavorite = menuItemFavoriteRepository.findByUserIdAndMenuItemId(request.getUserId(), request.getMenuItemId());

        if (existingFavorite.isPresent()) {
            return convertToResponse(existingFavorite.get());
        }

        // 중복이 아닌 경우 생성
        MenuItemFavorite menuItemFavorite = convertToEntity(request);
        MenuItemFavorite savedFavorite = menuItemFavoriteRepository.save(menuItemFavorite);
        return convertToResponse(savedFavorite);
    }

    public Boolean getIsExistMenuItemFavorite(Long userId, Long menuItemId){
        // 중복 여부 확인
        Optional<MenuItemFavorite> existingFavorite = menuItemFavoriteRepository.findByUserIdAndMenuItemId(userId, menuItemId);

        // 존재하면 true, 존재하지 않으면 false 반환
        return existingFavorite.isPresent();
    }

    public void deleteMenuItemFavoriteByContent(Long userId, Long menuItemId){
        Optional<MenuItemFavorite> existingFavorite = menuItemFavoriteRepository.findByUserIdAndMenuItemId(userId, menuItemId);

        if (existingFavorite.isPresent()) {
            menuItemFavoriteRepository.delete(existingFavorite.get());
        }
    }

    public void deleteMenuItemFavorite(Long id) {
        menuItemFavoriteRepository.deleteById(id);
    }

    private MenuItemFavoriteResponse convertToResponse(MenuItemFavorite menuItemFavorite) {
        MenuItemFavoriteResponse response = new MenuItemFavoriteResponse();
        response.setId(menuItemFavorite.getId());
        response.setUserId(menuItemFavorite.getUser().getId());
        response.setMenuItemId(menuItemFavorite.getMenuItem().getId());
        response.setCreatedAt(menuItemFavorite.getCreatedAt());
        response.setUpdatedAt(menuItemFavorite.getUpdatedAt());
        return response;
    }

    private MenuItemFavorite convertToEntity(MenuItemFavoriteRequest request) {
        MenuItemFavorite menuItemFavorite = new MenuItemFavorite();

        // User, MenuItem
        User user = userRepository.findById(request.getUserId()).orElseThrow(NoSuchElementException::new);
        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId()).orElseThrow(NoSuchElementException::new);

        menuItemFavorite.setUser(user);
        menuItemFavorite.setMenuItem(menuItem);

        return menuItemFavorite;
    }
}