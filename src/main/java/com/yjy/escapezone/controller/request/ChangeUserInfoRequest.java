package com.yjy.escapezone.controller.request;

import lombok.Getter;

@Getter
public class ChangeUserInfoRequest {

    private String email;
    private String password;

    private String nickname;
}
