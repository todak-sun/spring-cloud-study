package io.todak.study.microservice.userservice.mapper;

import io.todak.study.microservice.userservice.controller.model.OrderModel;
import io.todak.study.microservice.userservice.service.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderModel toModelForGetOne(OrderDto dto);

}
