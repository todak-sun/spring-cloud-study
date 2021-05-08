package io.todak.study.microservice.orderservice.controller.model;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class OrderModel {

    public static class Res {

        @Getter
        public static class Create {
            private String productId;
            private Integer quantity;
            private Integer unitPrice;
            private Integer totalPrice;
            private LocalDateTime createdAt;

            @Builder
            public Create(String productId, Integer quantity, Integer unitPrice, Integer totalPrice, LocalDateTime createdAt) {
                this.productId = productId;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
                this.totalPrice = totalPrice;
                this.createdAt = createdAt;
            }
        }
    }

}
