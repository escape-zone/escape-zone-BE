package com.yjy.escapezone.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class RegisterDto {

    private String email;
    private String password;

    private String nickname;

    private String name;

}
