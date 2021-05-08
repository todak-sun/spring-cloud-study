package io.todak.study.microservice.catalogservice.respository;

import io.todak.study.microservice.catalogservice.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    Optional<Catalog> findByProductId(String productId);

}
