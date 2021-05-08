package io.todak.study.microservice.catalogservice.service.dto;

import lombok.Builder;
import lombok.Getter;


public class CatalogDto {
    @Getter
    public static class GetList {
        private String productId;
        private Integer quantity;
        private Integer unitPrice;
        private Integer totalPrice;

        private String orderId;
        private String userId;

        @Builder
        public GetList(String productId, Integer quantity, Integer unitPrice, Integer totalPrice, String orderId, String userId) {
            this.productId = productId;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.totalPrice = totalPrice;
            this.orderId = orderId;
            this.userId = userId;
        }
    }
}
