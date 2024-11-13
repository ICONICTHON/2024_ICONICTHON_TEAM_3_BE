package com.akofood.server.repository;

import com.akofood.server.entity.MealVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealVoucherRepository extends JpaRepository<MealVoucher, Long> {
}