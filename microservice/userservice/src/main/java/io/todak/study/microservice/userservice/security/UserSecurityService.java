package io.todak.study.microservice.userservice.security;

import io.todak.study.microservice.userservice.entity.User;
import io.todak.study.microservice.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(email);
                });

        return new AuthenticatedUser(
                user.getEmail(),
                user.getUserId(),
                user.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }
}
