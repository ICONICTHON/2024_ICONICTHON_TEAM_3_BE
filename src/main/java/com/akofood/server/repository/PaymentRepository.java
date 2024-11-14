package com.akofood.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.akofood.server.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
