package io.todak.study.microservice.userservice.service;

import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.mapper.UserMapper;
import io.todak.study.microservice.userservice.repository.UserRepository;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserDto.Create createUser(UserDto.Create user) {

        User newUser = userMapper.dtoToEntity(user);
        newUser.setEncryptedPassword("encrypted_password");
        User savedUser = userRepository.save(newUser);
        UserDto.Create create = userMapper.entityToDto(savedUser);
        return create;
    }

}
