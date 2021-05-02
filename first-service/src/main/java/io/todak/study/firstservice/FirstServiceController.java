package io.todak.study.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class FirstServiceController {


    @GetMapping("/welcome")
    public String welcome() {
        log.info("welcome");
        return "Welcome to the First Service";
    }

    @GetMapping("/first-service/welcome")
    public String firstServiceWelcome() {
        log.info("first-service welcome");
        return "Welcome to the First Service";
    }

    @GetMapping("/first-service/message")
    public String firstServiceMessage(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Welcome to the First Service";
    }

    @GetMapping("/first-service/check")
    public String check() {
        return "Hi, there. This is a message from First Service";
    }
}
