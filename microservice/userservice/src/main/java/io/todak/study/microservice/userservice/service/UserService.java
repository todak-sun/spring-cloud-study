package io.todak.study.microservice.userservice.service;

import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.mapper.UserMapper;
import io.todak.study.microservice.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static io.todak.study.microservice.userservice.service.dto.UserDto.Create;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    public Create createUser(Create dto) {

        dto.encrpytPassword(passwordEncoder);
        User user = userMapper.convertToEntity(dto);

        userRepository.save(user);
        Create createdUser = userMapper.convertToDto(user);
        return createdUser;
    }

}
