package io.todak.study.microservice.userservice.controller;

import io.todak.study.microservice.userservice.config.TokenProperty;
import io.todak.study.microservice.userservice.controller.model.Greeting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class IndexController {

    private final Greeting greeting;
    private final TokenProperty tokenProperty;
    private final Environment env;

    @GetMapping("/health_check")
    public ResponseEntity<?> status() {
        Map<String, String> envMap = new HashMap<>();
        envMap.put("local.server.port", env.getProperty("local.server.port"));
        envMap.put("server.port", env.getProperty("server.port"));
        envMap.put("token.secret", env.getProperty("token.secret"));
        envMap.put("token.expiration-time", env.getProperty("token.expiration-time"));
        envMap.put("gateway.ip", env.getProperty("gateway.ip"));

        return ResponseEntity.status(HttpStatus.OK)
                .body(envMap);
    }

    @GetMapping("/welcome")
    public String welcome() {
        return greeting.getMessage();
    }

}
