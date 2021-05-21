package io.todak.study.microservice.orderservice.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;


public class OrderDto {

    @Getter
    public static class Create {
        private String productId;
        private Integer quantity;
        private Integer unitPrice;
        private Integer totalPrice;
        private String userId;
        private String orderId;

        @Builder
        public Create(String productId, Integer quantity, Integer unitPrice, String userId) {
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.userId = userId;
        }

        public void orderedBy(String userId) {
            this.userId = userId;
        }

        public void createOrderId() {
            this.orderId = UUID.randomUUID().toString();
        }

        public void calcTotalPrice() {
            this.totalPrice = this.unitPrice * this.quantity;
        }
    }

    @ToString
    @Getter
    public static class GetOne {
        private String productId;
        private Integer quantity;
        private Integer unitPrice;
        private Integer totalPrice;
        private String userId;
        private String orderId;

        @Builder
        public GetOne(String productId, Integer quantity, Integer unitPrice, Integer totalPrice, String userId, String orderId) {
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.totalPrice = totalPrice;
            this.userId = userId;
            this.orderId = orderId;
        }
    }


}
