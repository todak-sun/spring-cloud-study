package io.todak.study.microservice.userservice.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50, name = "NAME")
    private String name;

    @Column(nullable = false, unique = true, name = "UUID")
    private String userId;

    @Column(nullable = false, name = "PASSWORD", unique = true)
    private String encryptedPassword;

    @Builder
    public User(Long id, String email, String name, String userId, String encryptedPassword) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encryptedPassword = encryptedPassword;
    }
}
