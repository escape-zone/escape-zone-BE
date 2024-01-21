package com.yjy.escapezone.controller;

import com.yjy.escapezone.common.dto.ApiResponse;
import com.yjy.escapezone.controller.request.ChangeUserInfoRequest;
import com.yjy.escapezone.controller.request.LoginRequest;
import com.yjy.escapezone.controller.request.RegisterRequest;
import com.yjy.escapezone.controller.request.TokenDto;
import com.yjy.escapezone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<Long> register(@Validated @RequestBody RegisterRequest request) {

        return ApiResponse.ok("가입이 완료되었습니다.", null);
    }

    @PostMapping("/login")
    public ApiResponse<TokenDto> login(@Validated @RequestBody LoginRequest request) {
        TokenDto token = userService.login(request);
        return ApiResponse.ok(request.getEmail() + " 로그인 되었습니다.", token);
    }

    @GetMapping("/logout/{id}")
    public ApiResponse<?> logout(@PathVariable String id) {
        return ApiResponse.ok(id + " 로그아웃 되었습니다.", null);
    }


    @PostMapping("/change")
    public ApiResponse<?> changeUserInfo(@Validated @RequestBody ChangeUserInfoRequest request) {
        return ApiResponse.ok(null, null);
    }

}
