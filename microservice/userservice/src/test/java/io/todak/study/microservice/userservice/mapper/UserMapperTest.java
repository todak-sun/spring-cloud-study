package io.todak.study.microservice.userservice.mapper;

import io.todak.study.microservice.userservice.controller.model.UserModel;
import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    UserMapper userMapper = UserMapper.INSTANCE;

    @DisplayName("모델에서 Dto로 변환")
    @Test
    public void dtoToModel() {
        // given
        UserModel.Req.Create model = new UserModel.Req.Create();
        String email = "tjsdydwn@gmail.com";
        String name = "name";
        String password = "password";

        model.setEmail(email);
        model.setName(name);
        model.setPassword(password);

        // when
        UserDto.Create dto = userMapper.convertToDto(model);

        // then
        assertEquals(model.getName(), dto.getName());
        assertEquals(model.getPassword(), dto.getPassword());
        assertEquals(model.getEmail(), dto.getEmail());
        assertNotNull(dto.getCreatedAt());
    }

    @DisplayName("Dto에서 Entity 변환")
    @Test
    public void dtoToEntity() {
        // given
        String password = "password";

        UserDto.Create dto = UserDto.Create.builder()
                .email("tjsdydwn@gmail.com")
                .name("name")
                .password(password)
                .build();

        dto.encrpytPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder());

        // when
        User entity = userMapper.convertToEntity(dto);

        // then
        assertEquals(dto.getUserId(), entity.getUserId());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getName(), entity.getName());
        assertNotEquals(dto.getPassword(), password);
        assertEquals(dto.getPassword(), entity.getEncryptedPassword());
    }


}