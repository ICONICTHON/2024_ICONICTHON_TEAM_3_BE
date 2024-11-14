package com.akofood.server.repository;

import com.akofood.server.entity.MealVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealVoucherRepository extends JpaRepository<MealVoucher, Long> {
    List<MealVoucher> findByUserId(Long userId);

    // menuItemId 값과 used 상태를 기준으로 updatedAt이 가장 최근인 record를 찾음
    Optional<MealVoucher> findTopByMenuItemIdAndUsedTrueOrderByUpdatedAtDesc(Long menuItemId);
    Optional<MealVoucher> findByUniqueIdentifier(String uniqueIdentifier);
}