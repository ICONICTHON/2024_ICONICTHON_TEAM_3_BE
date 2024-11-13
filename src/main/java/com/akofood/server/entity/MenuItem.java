package com.akofood.server.entity;

import com.akofood.server.entity.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private String menuName;
    private BigDecimal menuPrice;
    private int dailyUsageLimit;
    private int dailyUsageCount;
    private int totalUsageCount;
    private int dailyVoucherSales;
    private int totalVoucherSales;
    private int likedCount;

}
