package io.todak.study.microservice.userservice.mapper;

import io.todak.study.microservice.userservice.controller.dto.UserModel;
import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto.Create modelToDto(UserModel.Req.Create model);

    User dtoToEntity(UserDto.Create dto);

    UserDto.Create entityToDto(User user);

    UserModel.Res.Create dtoToModel(UserDto.Create dto);

}
