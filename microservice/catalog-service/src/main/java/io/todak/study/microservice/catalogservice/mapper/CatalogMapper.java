package io.todak.study.microservice.catalogservice.mapper;

import io.todak.study.microservice.catalogservice.controller.model.CatalogModel;
import io.todak.study.microservice.catalogservice.entities.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    CatalogModel.Res toModelForList(Catalog catalog);
}
