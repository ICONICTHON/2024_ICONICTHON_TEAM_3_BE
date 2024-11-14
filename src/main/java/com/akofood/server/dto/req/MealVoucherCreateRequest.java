package com.akofood.server.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealVoucherCreateRequest {
    private Long userId;
    private Long restaurantId;
    private Long menuItemId;
}
