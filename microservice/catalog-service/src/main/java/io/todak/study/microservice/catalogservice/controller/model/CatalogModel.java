package io.todak.study.microservice.catalogservice.controller.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class CatalogModel {

    @Getter
    public static class Res {
        private String productId;
        private String productName;
        private Integer unitPrice;
        private Integer stock;
        private LocalDateTime createdAt;

        @Builder
        public Res(String productId, String productName, Integer unitPrice, Integer stock, LocalDateTime createdAt) {
            this.productId = productId;
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.stock = stock;
            this.createdAt = createdAt;
        }
    }

    public static class Req {

    }

}
