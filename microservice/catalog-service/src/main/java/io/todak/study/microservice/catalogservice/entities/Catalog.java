package io.todak.study.microservice.catalogservice.entities;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "CATALOG")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public int reduceStock(int amount) {
        int changedStock = stock - amount;
        if (changedStock < 0) {
            throw new RuntimeException("더 이상 수량을 줄일 수 없습니다.");
        }
        this.stock = changedStock;
        return this.stock;
    }
}
