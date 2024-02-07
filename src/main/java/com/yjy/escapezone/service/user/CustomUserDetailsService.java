package com.yjy.escapezone.service.user;

import com.yjy.escapezone.domain.user.User;
import com.yjy.escapezone.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(this::createUserDetails).orElseThrow(() -> new IllegalArgumentException(""));
    }

    private UserDetails createUserDetails(User user) {
        return User.builder()
                .email(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}