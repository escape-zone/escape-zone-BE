package com.yjy.escapezone.service.user;

import com.yjy.escapezone.common.jwt.TokenProvider;
import com.yjy.escapezone.controller.request.ChangeUserInfoRequest;
import com.yjy.escapezone.controller.request.LoginRequest;
import com.yjy.escapezone.controller.request.RegisterDto;
import com.yjy.escapezone.controller.request.TokenDto;
import com.yjy.escapezone.domain.user.User;
import com.yjy.escapezone.repository.user.UserRepository;
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
    public void register(RegisterDto request) {
        // 이메일 중복확인
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AlreadyUserInfoException("아이디");
        }

        // 닉네임 중복확인
        if(userRepository.findByNickname(request.getNickname()).isPresent()){
            throw new AlreadyUserInfoException("닉네임");
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

    public RegisterDto getUser(String name) {
        User user = userRepository.findByEmail(name).orElseThrow(() -> new IllegalArgumentException(""));
        return RegisterDto.builder().nickname(user.getNickname()).email(user.getEmail()).name(user.getUsername()).build();
    }

    public void changeUserInfo(ChangeUserInfoRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException(""));

        // 비밀번호 변경
        if(request.getPassword() != null) {
            user.updatePassword(passwordEncoder, request.getPassword());
        }
        // 닉네임 변경
        if(userRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new AlreadyUserInfoException("닉네임");
        }

        if(request.getNickname() != null) {
            user.updateNickname(request.getNickname());
        }
    }
}