package com.akofood.server.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItemFavoriteRequest {
    private Long userId;
    private Long menuItemId;
}
