package io.todak.study.microservice.userservice.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserModel {

    public static class Req {
        @Getter
        @Setter
        public static class Create {

            @NotNull
            @Size(min = 2)
            private String email;

            @NotNull
            @Size(min = 8)
            private String password;

            @NotNull
            @Size(min = 2)
            private String name;
        }
    }

    public static class Res {
        @Getter
        public static class Create {
            private String userId;
            private String email;
            private String name;
            private LocalDateTime createdAt;

            public Create(String userId, String email, String name, LocalDateTime createdAt) {
                this.userId = userId;
                this.email = email;
                this.name = name;
                this.createdAt = createdAt;
            }
        }
    }


}
