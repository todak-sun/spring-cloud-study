package io.todak.study.microservice.userservice.security;

import io.todak.study.microservice.userservice.config.TokenProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final UserSecurityService userSecurityService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProperty tokenProperty;

    // 인증에 대한 정의
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder)
        ;
    }

    // 권한에 대한 정의
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/health_check")
                .permitAll()
                .antMatchers("/**")
                .hasIpAddress("172.28.144.1")
                .and()
                .addFilter(authenticationFilter());
        http.headers().frameOptions().disable();
    }


    private AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(
                authenticationManager(),
                userSecurityService,
                tokenProperty);

        return authenticationFilter;
    }


}
