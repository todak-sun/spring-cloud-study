package io.todak.study.microservice.userservice.entity;

import lombok.Builder;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50, name = "NAME")
    private String name;

    @Column(nullable = false, unique = true, name = "UUID")
    private String userId;

    @Setter
    @Column(nullable = false, name = "PASSWORD")
    private String encryptedPassword;

    @Builder
    public User(Long id, String email, String name, String userId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

}
