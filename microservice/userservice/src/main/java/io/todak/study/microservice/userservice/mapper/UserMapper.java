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

    UserDto.Create convertToDto(UserModel.Req.Create model);

    @Mappings({
            @Mapping(target = "encryptedPassword", source = "dto.password")
    })
    User convertToEntity(UserDto.Create dto);

    UserModel.Res.Create convertToModel(UserDto.Create dto);

    UserDto.Create convertToDto(User user);
}
