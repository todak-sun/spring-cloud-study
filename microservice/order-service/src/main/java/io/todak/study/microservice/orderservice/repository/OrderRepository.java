package io.todak.study.microservice.orderservice.repository;

import io.todak.study.microservice.orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderId(String orderId);

    List<Order> findAllByUserId(String userId);

}
