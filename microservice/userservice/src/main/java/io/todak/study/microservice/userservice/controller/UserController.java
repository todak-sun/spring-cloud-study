package io.todak.study.microservice.userservice.controller;

import io.todak.study.microservice.userservice.controller.dto.UserModel;
import io.todak.study.microservice.userservice.mapper.UserMapper;
import io.todak.study.microservice.userservice.service.UserService;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import io.todak.study.microservice.userservice.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final Greeting greeting;
    private final UserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

    @PostMapping
    public String createUser(@RequestBody UserModel.Req.Create user) {

        UserDto.Create create = userMapper.modelToDto(user);
        UserDto.Create dto = userService.createUser(create);

        return "Create user method is called";
    }


}
