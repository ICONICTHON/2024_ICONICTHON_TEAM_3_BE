package com.akofood.server.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealVoucherRequest {
    private Long userId;
    private Long restaurantId;
    private Long menuItemId;
    private String uniqueIdentifier;
    private int orderNumber;
    private boolean used;
}
