package io.todak.study.microservice.userservice.service;

import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.mapper.UserMapper;
import io.todak.study.microservice.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.todak.study.microservice.userservice.service.dto.UserDto.*;
import static io.todak.study.microservice.userservice.service.dto.UserDto.Create;
import static io.todak.study.microservice.userservice.service.dto.UserDto.GetOne;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    public Create createUser(Create dto) {
        dto.createUserId();
        dto.encrpytPassword(passwordEncoder);
        User user = userMapper.toEntity(dto);

        userRepository.save(user);
        Create createdUser = userMapper.toDtoForCreate(user);
        return createdUser;
    }


    public GetOne getUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .map(userMapper::toDtoForGetOne)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User name not found");
                });
    }

    public List<GetList> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDtoForList)
                .collect(Collectors.toList());
    }


}
