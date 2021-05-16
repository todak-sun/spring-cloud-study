package io.todak.study.microservice.userservice.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDto {

    @Getter
    public static class Create {

        private String email;
        private String name;
        private String password;
        private String userId;
        private LocalDateTime createdAt;


        public void createUserId() {
            this.userId = UUID.randomUUID().toString();
        }

        public void encrpytPassword(PasswordEncoder passwordEncoder) {
            this.password = passwordEncoder.encode(this.password);
        }

        @Builder
        public Create(String email, String name, String password, String userId, LocalDateTime createdAt) {
            this.email = email;
            this.name = name;
            this.password = password;
            this.userId = userId;
            this.createdAt = createdAt;
        }
    }

    @Getter
    public static class GetOne {
        private final String email;
        private final String name;
        private final String userId;

        @Setter
        private List<OrderDto> orders;

        @Builder
        public GetOne(String email, String name, String userId) {
            this.email = email;
            this.name = name;
            this.userId = userId;
            this.orders = new ArrayList<>();
        }
    }

    @Getter
    public static class GetList {
        private String email;
        private String name;
        private String userId;

        @Builder
        public GetList(String email, String name, String userId) {
            this.email = email;
            this.name = name;
            this.userId = userId;
        }
    }

}
