package com.yjy.escapezone.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersRegisterRequest {

    private String email;
    private String password;

    private String nickname;

    private String name;

}
