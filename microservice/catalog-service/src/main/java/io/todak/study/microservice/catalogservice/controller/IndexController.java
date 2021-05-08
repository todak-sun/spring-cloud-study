package io.todak.study.microservice.catalogservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/catalog-service")
@Controller
public class IndexController {

    @GetMapping("/health_check")
    public ResponseEntity<?> healthCheck(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("It's Working in Catalog Service on Port %s", request.getServerPort()));
    }

}
