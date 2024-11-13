package com.akofood.server.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestaurantResponse {
    private Long id;
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private String weekdayHours;
    private String weekendHours;
    private String holidayHours;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}