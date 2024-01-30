package com.yjy.escapezone.common.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    private boolean success;
    private T data;

    private String message;

    public static <T> ApiResponse<T> ok(String message, T body) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(body)
                .message(message)
                .build();
    }

    //임시 api개발을 위한 응답값
    public static <T> ApiResponse<T> ok() {
        return ApiResponse.<T>builder()
                .success(true)
                .data(null)
                .message("ok")
                .build();
    }

    public static <T> ApiResponse<T> from(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
}
