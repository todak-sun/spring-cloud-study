package io.todak.study.microservice.gatewayservice.filter;

import io.jsonwebtoken.Jwts;
import io.todak.study.microservice.gatewayservice.config.TokenProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    public static final String BEARER = "Bearer ";
    private TokenProperty tokenProperty;

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Autowired
    public void setTokenProperty(TokenProperty tokenProperty) {
        log.info("tokenProperty : {}", tokenProperty);
        this.tokenProperty = tokenProperty;
    }

    // login -> token -> users (with token) -> header(include token)
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            HttpHeaders headers = request.getHeaders();

            if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace(BEARER, "").trim();

            if (!isValidJwt(jwt)) {
                return onError(exchange, "JWT is not valid", HttpStatus.UNAUTHORIZED);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isValidJwt(String jwt) {
        boolean isValid = true;
        String subject = null;
        try {
            subject = Jwts.parser()
                    .setSigningKey(tokenProperty.getSecret())
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            log.error("error : {}", e);
            isValid = false;
        }

        if (subject == null || subject.isEmpty()) {
            isValid = false;
        }

        return isValid;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String errorMessage, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        log.error("error : {}", errorMessage);
        return response.setComplete();
    }

    public static class Config {

    }
}
