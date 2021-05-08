package io.todak.study.microservice.userservice.mapper;

import io.todak.study.microservice.userservice.controller.model.UserModel;
import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserMapper 테스트")
class UserMapperTest {

    UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    @DisplayName("toDtoForCreateFromEntity, entity -> dto 테스트")
    public void toDtoForCreateFromEntity() throws Exception {
        //given
        User entity = createEntity();
        //when
        UserDto.Create dto = userMapper.toDtoForCreate(entity);

        //then
        assertEquals(entity.getEmail(), dto.getEmail());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getUserId(), dto.getUserId());
        assertNotEquals(entity.getEncryptedPassword(), dto.getPassword());
    }


    @DisplayName("toDtoForCreate, Model -> Dto 테스트")
    @Test
    public void toDtoForCreateFromModel() {
        // given
        UserModel.Req.Create model = new UserModel.Req.Create();
        String email = "tjsdydwn@gmail.com";
        String name = "name";
        String password = "password";

        model.setEmail(email);
        model.setName(name);
        model.setPassword(password);

        // when
        UserDto.Create dto = userMapper.toDtoForCreate(model);

        // then
        assertEquals(model.getName(), dto.getName());
        assertEquals(model.getPassword(), dto.getPassword());
        assertEquals(model.getEmail(), dto.getEmail());
    }

    @DisplayName("toDtoForList, entity -> dto 테스트")
    @Test
    public void toDtoForListTest() {
        //given
        User entity = createEntity();

        //when
        UserDto.GetList dto = userMapper.toDtoForList(entity);

        //then
        assertEquals(entity.getEmail(), dto.getEmail());
        assertEquals(entity.getUserId(), dto.getUserId());
        assertEquals(entity.getName(), dto.getName());
    }

    @DisplayName("toDto, entity -> dto 테스트")
    @Test
    public void toDtoForGetOne() throws Exception {
        //given
        User entity = createEntity();

        //when
        UserDto.GetOne dto = userMapper.toDtoForGetOne(entity);

        //then
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getEmail(), dto.getEmail());
        assertEquals(entity.getUserId(), dto.getUserId());
    }

    @DisplayName("toModelForCreate, dto -> Model 테스트")
    @Test
    public void toModelForCreateTest() throws Exception {
        //given
        UserDto.Create dto = UserDto.Create
                .builder()
                .name("tjsdydwn@gmail.com")
                .userId(UUID.randomUUID().toString())
                .name("tjsdydwn")
                .password("password")
                .build();

        //when
        UserModel.Res.Create model = userMapper.toModelForCreate(dto);

        //then
        assertEquals(dto.getEmail(), model.getEmail());
        assertEquals(dto.getName(), model.getName());
        assertEquals(dto.getUserId(), model.getUserId());
    }

    @DisplayName("toModelForList, dto -> Model 테스트")
    @Test
    public void toModelForListTest() {
        //given
        UserDto.GetList dto = UserDto.GetList
                .builder()
                .userId("userId")
                .name("name")
                .email("email")
                .build();

        //when
        UserModel.Res.GetList model = userMapper.toModelForList(dto);

        //then
        assertEquals(dto.getEmail(), model.getEmail());
        assertEquals(dto.getUserId(), model.getUserId());
        assertEquals(dto.getName(), model.getName());
    }

    @DisplayName("toEntity, dto -> Entity 테스트")
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
        User entity = userMapper.toEntity(dto);

        // then
        assertEquals(dto.getUserId(), entity.getUserId());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getName(), entity.getName());
        assertNotEquals(dto.getPassword(), password);
        assertEquals(dto.getPassword(), entity.getEncryptedPassword());
    }

    private User createEntity() {
        String email = "tjsdydwn@gmail.com";
        String yongjoo = "yongjoo";
        String password = "password";
        long id = 1L;
        return User.builder().userId(UUID.randomUUID().toString())
                .email(email)
                .name(yongjoo)
                .encryptedPassword(password)
                .id(id)
                .build();
    }

}