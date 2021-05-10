package io.todak.study.microservice.userservice.controller;

import io.todak.study.microservice.userservice.config.TokenProperty;
import io.todak.study.microservice.userservice.controller.model.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class IndexController {

    private final Greeting greeting;
    private final TokenProperty tokenProperty;

    @GetMapping("/health_check")
    public ResponseEntity<?> status(HttpServletRequest request) {
        log.info("tokenProperty : {}", tokenProperty);
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("It's Working in User Service on Port %s", request.getServerPort()));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

}
