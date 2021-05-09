package io.todak.study.microservice.orderservice.controller.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
            private String orderId;

            @Builder
            public Create(String productId, Integer quantity, Integer unitPrice, Integer totalPrice, LocalDateTime createdAt, String orderId) {
                this.productId = productId;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
                this.totalPrice = totalPrice;
                this.createdAt = createdAt;
                this.orderId = orderId;
            }
        }

        @Getter
        public static class GetList {
            private String productId;
            private Integer quantity;
            private Integer unitPrice;
            private Integer totalPrice;
            private LocalDateTime createdAt;
            private String orderId;

            @Builder
            public GetList(String productId, Integer quantity, Integer unitPrice, Integer totalPrice, LocalDateTime createdAt, String orderId) {
                this.productId = productId;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
                this.totalPrice = totalPrice;
                this.createdAt = createdAt;
                this.orderId = orderId;
            }
        }
    }

    public static class Req {
        @Getter
        @Setter
        public static class Create {
            private String productId;
            private Integer quantity;
            private Integer unitPrice;
        }
    }

}
