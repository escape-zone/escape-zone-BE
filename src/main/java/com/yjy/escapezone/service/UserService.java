package com.yjy.escapezone.service;

import com.yjy.escapezone.common.jwt.TokenProvider;
import com.yjy.escapezone.controller.request.LoginRequest;
import com.yjy.escapezone.controller.request.RegisterRequest;
import com.yjy.escapezone.controller.request.TokenDto;
import com.yjy.escapezone.domain.users.User;
import com.yjy.escapezone.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /**
     * 로그인
     * @return TokenDto
     */
    public TokenDto login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.createToken(authenticate);
    }

    /**
     * 회원가입
     */
    public void register(RegisterRequest request) {
        // 이메일 중복확인
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new IllegalStateException("이미 가입된 아이디입니다.");
        }

        // 닉네임 중복확인
        if(userRepository.findByNickname(request.getNickname()).isPresent()){
            throw new IllegalStateException("중복된 닉네임입니다.");
        }

        // DB 저장
        User user = User.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .point(0L)
                        .build();
        userRepository.save(user);
    }
}
