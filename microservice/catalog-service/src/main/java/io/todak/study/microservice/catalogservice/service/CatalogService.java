package io.todak.study.microservice.catalogservice.service;

import io.todak.study.microservice.catalogservice.entities.Catalog;
import io.todak.study.microservice.catalogservice.mapper.CatalogMapper;
import io.todak.study.microservice.catalogservice.respository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper = CatalogMapper.INSTANCE;

    public List<Catalog> getAllCatalogs() {
        List<Catalog> catalogs = catalogRepository.findAll();

        return catalogs;
    }

}
