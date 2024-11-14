package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String nickname;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
