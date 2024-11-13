package com.akofood.server.repository;

import com.akofood.server.entity.MenuItemFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemFavoriteRepository extends JpaRepository<MenuItemFavorite, Long> {
}