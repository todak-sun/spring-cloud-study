package io.todak.study.microservice.userservice.controller;

import io.todak.study.microservice.userservice.controller.model.UserModel;
import io.todak.study.microservice.userservice.mapper.UserMapper;
import io.todak.study.microservice.userservice.service.UserService;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import io.todak.study.microservice.userservice.vo.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserModel.Req.Create user) {

        UserDto.Create create = userMapper.convertToDto(user);
        UserDto.Create userDto = userService.createUser(create);

        UserModel.Res.Create response = userMapper.convertToModel(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


}
