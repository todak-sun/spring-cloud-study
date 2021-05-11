package io.todak.study.microservice.gatewayservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class GeneralConfigurer {

    private final Environment env;

    @Bean
    public TokenProperty tokenProperty() {
        return new TokenProperty();
    }

    // for httptrace
    @Bean
    public HttpTraceRepository httpTraceRepository() {
        return new InMemoryHttpTraceRepository();
    }

    @PostConstruct
    public void postConstruct() {
        Map<String, Object> envMap = new HashMap<>();
        envMap.put("local.server.port", env.getProperty("local.server.port"));
        envMap.put("server.port", env.getProperty("server.port"));
        envMap.put("token.secret", env.getProperty("token.secret"));
        envMap.put("token.expiration-time", env.getProperty("token.expiration-time"));
        envMap.put("gateway.ip", env.getProperty("gateway.ip"));
        log.info("info : {}", envMap);
    }
}
