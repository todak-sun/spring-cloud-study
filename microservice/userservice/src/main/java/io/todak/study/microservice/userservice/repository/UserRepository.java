package io.todak.study.microservice.userservice.repository;

import io.todak.study.microservice.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
