package com.akofood.server.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateForm {

    private Long userId;
    private Long menuItemId;
    private String name;
    private int totalPrice;

}

//받고싶은 정보 더 받아서 넣어주기!!!