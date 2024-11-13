package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MenuCommentResponse {
    private Long id;
    private Long userId;
    private Long menuItemId;
    private String commentContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}