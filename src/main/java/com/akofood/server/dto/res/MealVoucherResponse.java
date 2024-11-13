package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MealVoucherResponse {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private Long menuItemId;
    private String uniqueIdentifier;
    private int orderNumber;
    private boolean used;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}