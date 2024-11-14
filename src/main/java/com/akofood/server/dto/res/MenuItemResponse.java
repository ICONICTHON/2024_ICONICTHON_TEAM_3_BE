package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MenuItemResponse {
    private Long id;
    private Long restaurantId;
    private String menuName;
    private BigDecimal menuPrice;
    private String operatingHours;
    private int dailyUsageLimit;
    private int dailyUsageCount;
    private int totalUsageCount;
    private int dailyVoucherSales;
    private int totalVoucherSales;
    private int likedCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}