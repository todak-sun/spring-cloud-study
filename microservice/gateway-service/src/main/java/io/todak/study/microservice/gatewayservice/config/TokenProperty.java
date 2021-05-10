package io.todak.study.microservice.gatewayservice.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ToString
@Configuration
@ConfigurationProperties(value = "token")
@Getter
@Setter
public class TokenProperty {
    private String secret;
}
