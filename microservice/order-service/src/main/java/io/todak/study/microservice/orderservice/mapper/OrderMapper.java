package io.todak.study.microservice.orderservice.mapper;

import io.todak.study.microservice.orderservice.controller.model.OrderModel;
import io.todak.study.microservice.orderservice.entities.Order;
import io.todak.study.microservice.orderservice.service.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntityForCreate(OrderDto.Create dto);
    OrderDto.GetOne toDtoForGetOne(Order entity);
    OrderDto.Create toDtoForCreate(OrderModel.Req.Create model);
    OrderModel.Res.Create toModelForCreate(OrderDto.GetOne dto);
    OrderModel.Res.GetList toModelForGetList(Order order);
}
