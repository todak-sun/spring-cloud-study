package io.todak.study.microservice.orderservice.controller;

import io.todak.study.microservice.orderservice.controller.model.OrderModel;
import io.todak.study.microservice.orderservice.entities.Order;
import io.todak.study.microservice.orderservice.mapper.OrderMapper;
import io.todak.study.microservice.orderservice.service.OrderService;
import io.todak.study.microservice.orderservice.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @PostMapping("{userId}/orders")
    public ResponseEntity<?> createOrder(
            @PathVariable String userId,
            @RequestBody OrderModel.Req.Create orderDetails) {

        OrderDto.Create dto = orderMapper.toDtoForCreate(orderDetails);
        dto.orderedBy(userId);

        OrderDto.GetOne order = orderService.createOrder(dto);

        OrderModel.Res.Create response = orderMapper.toModelForCreate(order);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<?> getOrders(@PathVariable String userId) {
        List<Order> orders = orderService.getAllOrdersByUserId(userId);
        List<OrderModel.Res.GetList> response = orders.stream()
                .map(orderMapper::toModelForGetList).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
