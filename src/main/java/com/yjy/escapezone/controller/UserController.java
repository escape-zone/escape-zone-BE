package com.yjy.escapezone.controller;

import com.yjy.escapezone.common.dto.ApiResponse;
import com.yjy.escapezone.controller.request.LoginRequest;
import com.yjy.escapezone.controller.request.UsersRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @PostMapping("/register")
    public ApiResponse<Long> register(@Validated @RequestBody UsersRegisterRequest request) {

        return ApiResponse.ok("가입이 완료되었습니다.", null);
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@Validated @RequestBody LoginRequest request) {
        return ApiResponse.ok(request.getEmail() + " 로그인 되었습니다.", null);
    }

    @GetMapping("/logout/{id}")
    public ApiResponse<?> logout(@PathVariable String id) {
        return ApiResponse.ok(id + " 로그아웃 되었습니다.", null);
    }

}
