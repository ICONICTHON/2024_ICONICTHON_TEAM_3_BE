package com.akofood.server.repository;

import com.akofood.server.entity.MenuItemFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuItemFavoriteRepository extends JpaRepository<MenuItemFavorite, Long> {

    // 특정 userId에 해당하는 MenuItemFavorite 리스트 검색
    List<MenuItemFavorite> findByUserId(@Param("userId") Long userId);

    // 특정 userId와 menuItemId의 조합으로 중복된 MenuItemFavorite 조회
    @Query("SELECT m FROM MenuItemFavorite m WHERE m.user.id = :userId AND m.menuItem.id = :menuItemId")
    Optional<MenuItemFavorite> findByUserIdAndMenuItemId(@Param("userId") Long userId, @Param("menuItemId") Long menuItemId);
}