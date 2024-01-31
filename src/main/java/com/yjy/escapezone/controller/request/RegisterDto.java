package com.yjy.escapezone.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@SuppressWarnings("unused")
public class RegisterDto {

    @Email
    @NotBlank(message = "email 은 필수값입니다.")
    private String email;
    @NotBlank(message = "password 은 필수값입니다.")
    private String password;

    @NotBlank(message = "nickname 은 필수값입니다.")
    private String nickname;

    @NotBlank(message = "name 은 필수값입니다.")
    private String name;

}
