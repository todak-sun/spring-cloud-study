package io.todak.study.microservice.userservice.mapper;

import io.todak.study.microservice.userservice.controller.model.UserModel;
import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = OrderMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    UserDto.Create toDtoForCreate(User user);

    @Mappings({
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "createdAt", ignore = true)
    })
    UserDto.Create toDtoForCreate(UserModel.Req.Create model);

    UserDto.GetList toDtoForList(User user);

    UserDto.GetOne toDtoForGetOne(User user);

    UserModel.Res.Create toModelForCreate(UserDto.Create dto);

    UserModel.Res.GetList toModelForList(UserDto.GetList getList);

    @Mappings({
            @Mapping(target = "orders", source = "orders")
    })
    UserModel.Res.GetOne toModelForGetOne(UserDto.GetOne dto);

    @Mappings({
            @Mapping(target = "encryptedPassword", source = "dto.password"),
            @Mapping(target = "id", ignore = true)
    })
    User toEntity(UserDto.Create dto);


}
