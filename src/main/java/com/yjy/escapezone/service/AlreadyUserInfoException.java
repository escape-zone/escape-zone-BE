package com.yjy.escapezone.service;

public class AlreadyUserInfoException extends RuntimeException{

    public AlreadyUserInfoException(String userInfo) {
        super("중복된 " + userInfo + "입니다.");

    }
}
