package com.akofood.server.repository;

import com.akofood.server.entity.MenuItemFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuItemFavoriteRepository extends JpaRepository<MenuItemFavorite, Long> {

    // 특정 userId에 해당하는 MenuItemFavorite 리스트 검색
    List<MenuItemFavorite> findByUserId(@Param("userId") Long userId);

}