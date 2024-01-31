package com.yjy.escapezone.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@SuppressWarnings("unused")
public class LoginRequest {

    @Email
    @NotBlank(message = "email 은 필수값 입니다")
    private String email;
    @NotBlank(message = "password 은 필수값 입니다")
    private String password;
}
