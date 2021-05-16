package io.todak.study.microservice.userservice.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
public class OrderDto {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDateTime createdAt;
    private String orderId;

//    @Builder
//    public OrderDto(String productId, Integer quantity, Integer unitPrice, Integer totalPrice, LocalDateTime createdAt, String orderId) {
//        this.productId = productId;
//        this.quantity = quantity;
//        this.unitPrice = unitPrice;
//        this.totalPrice = totalPrice;
//        this.createdAt = createdAt;
//        this.orderId = orderId;
//    }
}
