package com.akofood.server.repository;

import com.akofood.server.entity.MealVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealVoucherRepository extends JpaRepository<MealVoucher, Long> {
    List<MealVoucher> findByUserId(Long userId);
}