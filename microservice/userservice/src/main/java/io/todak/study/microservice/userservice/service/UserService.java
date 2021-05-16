package io.todak.study.microservice.userservice.service;

import feign.FeignException;
import io.todak.study.microservice.userservice.client.OrderServiceClient;
import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.mapper.UserMapper;
import io.todak.study.microservice.userservice.repository.UserRepository;
import io.todak.study.microservice.userservice.service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.todak.study.microservice.userservice.service.dto.UserDto.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    private final OrderServiceClient orderServiceClient;
//    private final Environment env;
//    private final RestTemplate restTemplate;

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
                .map(user -> {
                    GetOne getOne = userMapper.toDtoForGetOne(user);

                    // Using as RestTemplate
//                    String orderUrl = String.format(env.getProperty("order-service.url"), userId);
//                    ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
//                            orderUrl,
//                            HttpMethod.GET,
//                            null,
//                            new ParameterizedTypeReference<List<OrderDto>>() {
//
//                            });
//                    List<OrderDto> orders = response.getBody();
//                    getOne.setOrders(orders);
                    // Using as RestTemplate


                    List<OrderDto> orders = orderServiceClient.getOrders(userId);
//                    try {
//                        orders = orderServiceClient.getOrders(userId);
//                    } catch (FeignException e){
//                        log.error("error : {}", e.getMessage());
//                    }
                    getOne.setOrders(orders);
                    return getOne;
                })
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
