package io.todak.study.microservice.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class IndexController {

    @GetMapping("/health_check")
    public ResponseEntity<?> healthCheck(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("It's Working in Catalog Service on Port %s", request.getServerPort()));
    }

}
