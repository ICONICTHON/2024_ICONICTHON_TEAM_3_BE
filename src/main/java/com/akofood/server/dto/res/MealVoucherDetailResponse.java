package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MealVoucherDetailResponse {
    private Long id;
    private String uniqueIdentifier;
    private int orderNumber;
    private boolean used;

    // 식당 ID와 이름
    private Long restaurantId;
    private String restaurantName;

    // 메뉴 ID와 정보
    private Long menuItemId;
    private String menuName;
    private BigDecimal menuPrice;
    private String operatingHours;
    private int dailyUsageLimit;
    private int dailyUsageCount;
    private int totalUsageCount;
    private int dailyVoucherSales;
    private int totalVoucherSales;
    private int likedCount;
}