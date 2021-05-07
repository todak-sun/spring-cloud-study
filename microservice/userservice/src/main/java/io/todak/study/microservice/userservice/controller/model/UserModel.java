package io.todak.study.microservice.userservice.controller.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {

    public static class Req {
        @Getter
        @Setter
        public static class Create {

            @NotNull(message = "Email cannot be null")
            @Size(min = 2, message = "Email not be less than two characters")
            private String email;

            @NotNull(message = "Name connot be null")
            @Size(min = 2, message = "Name not be less than two characters")
            private String name;

            @NotNull(message = "Password cannot be null")
            @Size(min = 8, message = "Password must be equal or grater than 8 characters")
            private String password;
        }
    }

    public static class Res {
        @Getter
        public static class Create {
            private String userId;
            private String email;
            private String name;

            @Builder
            public Create(String userId, String email, String name) {
                this.userId = userId;
                this.email = email;
                this.name = name;
            }
        }
    }


}
