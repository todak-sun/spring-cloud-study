package io.todak.study.microservice.userservice.controller;

import io.todak.study.microservice.userservice.controller.model.UserModel;
import io.todak.study.microservice.userservice.mapper.UserMapper;
import io.todak.study.microservice.userservice.service.UserService;
import io.todak.study.microservice.userservice.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service")
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper = UserMapper.INSTANCE;


    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserModel.Req.Create user) {

        UserDto.Create create = userMapper.toDtoForCreate(user);
        UserDto.Create userDto = userService.createUser(create);

        UserModel.Res.Create response = userMapper.toModelForCreate(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<?> fetchUsers() {
        List<UserDto.GetList> allUsers = userService.getAllUsers();
        List<UserModel.Res.GetList> response = allUsers.stream().map(userMapper::toModelForList).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> fetchOneUser(@PathVariable String userId) {
        UserDto.GetOne dto = userService.getUserByUserId(userId);
        UserModel.Res.GetOne response = userMapper.toModelForGetOne(dto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


}
