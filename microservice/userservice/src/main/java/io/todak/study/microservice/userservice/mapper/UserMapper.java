package io.todak.study.microservice.userservice.mapper;

import io.todak.study.microservice.userservice.controller.model.UserModel;
import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto.Create toDtoForCreate(User user);

    UserDto.Create toDtoForCreate(UserModel.Req.Create model);

    UserDto.GetList toDtoForList(User user);

    UserDto.GetOne toDtoForGetOne(User user);

    UserModel.Res.Create toModelForCreate(UserDto.Create dto);

    UserModel.Res.GetList toModelForList(UserDto.GetList getList);

    UserModel.Res.GetOne toModelForGetOne(UserDto.GetOne dto);

    @Mappings({
            @Mapping(target = "encryptedPassword", source = "dto.password")
    })
    User toEntity(UserDto.Create dto);



}
