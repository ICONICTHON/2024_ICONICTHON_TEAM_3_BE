package com.akofood.server.service;

import com.akofood.server.dto.res.UserResponse;
import com.akofood.server.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.akofood.server.entity.Order;
import com.akofood.server.entity.Payment;
import com.akofood.server.entity.PaymentStatus;
import com.akofood.server.repository.OrderRepository;
import com.akofood.server.repository.PaymentRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final UserService userService;

    public Order autoOrder(User user) {

        // 임시 결제내역 생성
        Payment payment = Payment.builder()
                .price(1000L)
                .status(PaymentStatus.READY)
                .build();

        paymentRepository.save(payment);

        // 주문 생성
        Order order = Order.builder()
                .user(user)
                .price(1000L)
                .itemName("1달러샵 상품")
                .orderUid(UUID.randomUUID().toString())
                .payment(payment)
                .build();

        return orderRepository.save(order);
    }
}

