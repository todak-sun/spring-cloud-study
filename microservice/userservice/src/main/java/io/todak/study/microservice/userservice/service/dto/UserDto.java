package io.todak.study.microservice.userservice.service.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDto {

    @Getter
    public static class Create {

        private String email;
        private String name;
        private String password;
        private String userId;
        private LocalDateTime createdAt;


        public void encrpytPassword(PasswordEncoder passwordEncoder) {
            this.password = passwordEncoder.encode(this.password);
        }

        @Builder
        public Create(String email, String name, String password) {
            this.email = email;
            this.name = name;
            this.password = password;
            this.userId = UUID.randomUUID().toString();
            this.createdAt = LocalDateTime.now();
        }
    }

}
