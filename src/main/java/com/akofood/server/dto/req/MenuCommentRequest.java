package com.akofood.server.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuCommentRequest {
    private Long userId;
    private Long menuItemId;
    private String commentContent;
}