package io.todak.study.microservice.userservice.controller;

import io.todak.study.microservice.userservice.controller.model.Greeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/user-service")
@RestController
public class IndexController {

    private final Greeting greeting;

    @GetMapping("/health_check")
    public ResponseEntity<?> status(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("It's Working in User Service on Port %s", request.getServerPort()));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

}
