package io.todak.study.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        log.info("welcome");
        return "Welcome to the Second Service";
    }

    @GetMapping("/second-service/welcome")
    public String firstServiceWelcome() {
        log.info("second-service welcome");
        return "Welcome to the Second Service";
    }

    @GetMapping("/second-service/message")
    public String secondServiceMessage(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Welcome to the Second Service";
    }

    @GetMapping("/second-service/check")
    public String check() {
        return "Hi, there. This is a message from Second Service";
    }

}
