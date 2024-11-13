package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MenuItemFavoriteResponse {
    private Long id;
    private Long userId;
    private Long menuItemId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}