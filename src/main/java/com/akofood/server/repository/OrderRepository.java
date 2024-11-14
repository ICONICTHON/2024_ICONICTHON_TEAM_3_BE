package com.akofood.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.akofood.server.entity.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o" +
            " left join fetch o.payment p" +
            " left join fetch o.user m" + //member를 user로 수정함!
            " where o.orderUid = :orderUid")
    Optional<Order> findOrderAndPaymentAndMember(String orderUid);

    @Query("select o from Order o" +
            " left join fetch o.payment p" +
            " where o.orderUid = :orderUid")
    Optional<Order> findOrderAndPayment(String orderUid);
}

