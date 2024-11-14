package com.akofood.server.entity;

import com.akofood.server.entity.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealVoucher extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    private String uniqueIdentifier;
    private int orderNumber;
    private boolean used;

    @PrePersist
    private void generateUniqueIdentifier() {
        if (uniqueIdentifier == null || uniqueIdentifier.isEmpty()) {
            uniqueIdentifier = UUID.randomUUID().toString();
        }
    }

}