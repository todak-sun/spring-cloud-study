package io.todak.study.microservice.catalogservice.controller;

import io.todak.study.microservice.catalogservice.controller.model.CatalogModel;
import io.todak.study.microservice.catalogservice.entities.Catalog;
import io.todak.study.microservice.catalogservice.mapper.CatalogMapper;
import io.todak.study.microservice.catalogservice.service.CatalogService;
import io.todak.study.microservice.catalogservice.service.dto.CatalogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/catalog-service")
@Controller
public class CatalogController {

    private final CatalogMapper catalogMapper = CatalogMapper.INSTANCE;
    private final CatalogService catalogService;


    @GetMapping("/catalogs")
    public ResponseEntity<?> fetchCatalogs() {
        List<Catalog> dtos = catalogService.getAllCatalogs();

        List<CatalogModel.Res> response = dtos.stream()
                .map(catalogMapper::toModelForList)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
