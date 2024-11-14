package com.akofood.server.dto.req;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuItemRequest {
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
}
