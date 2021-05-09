package io.todak.study.microservice.orderservice.service;

import io.todak.study.microservice.orderservice.entities.Order;
import io.todak.study.microservice.orderservice.mapper.OrderMapper;
import io.todak.study.microservice.orderservice.repository.OrderRepository;
import io.todak.study.microservice.orderservice.service.dto.OrderDto;
import io.todak.study.microservice.orderservice.service.dto.OrderDto.Create;
import io.todak.study.microservice.orderservice.service.dto.OrderDto.GetOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    public GetOne createOrder(Create orderDetails) {

        orderDetails.createOrderId();
        orderDetails.calcTotalPrice();
        Order order = orderMapper.toEntityForCreate(orderDetails);

        Order savedOrder = orderRepository.save(order);
        GetOne getOne = orderMapper.toDtoForGetOne(savedOrder);
        return getOne;
    }

    public List<Order> getAllOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders;
    }

    public GetOne getOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .map(orderMapper::toDtoForGetOne)
                .orElseThrow(() -> {
                    throw new NoSuchElementException(orderId);
                });
    }


}
