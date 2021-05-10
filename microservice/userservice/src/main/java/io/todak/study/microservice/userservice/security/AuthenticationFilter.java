package io.todak.study.microservice.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.todak.study.microservice.userservice.config.TokenProperty;
import io.todak.study.microservice.userservice.controller.model.LoginModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String BEARER = "Bearer ";

    private final UserSecurityService userSecurityService;
    private final TokenProperty tokenProperty;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                UserSecurityService userSecurityService,
                                TokenProperty tokenProperty) {
        super(authenticationManager);
        this.userSecurityService = userSecurityService;
        this.tokenProperty = tokenProperty;
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            LoginModel credential = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credential.getEmail(), credential.getPassword(), new ArrayList<>());
            return getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        log.debug("principal : {}", (AuthenticatedUser) authResult.getPrincipal());
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authResult.getPrincipal();
        authenticatedUser.getUserId();

        String token = BEARER.concat(Jwts.builder()
                .setSubject(authenticatedUser.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + tokenProperty.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, tokenProperty.getSecret())
                .compact());

        response.addHeader(HttpHeaders.AUTHORIZATION, token);
        response.addHeader("userId", authenticatedUser.getUserId());
    }


}
