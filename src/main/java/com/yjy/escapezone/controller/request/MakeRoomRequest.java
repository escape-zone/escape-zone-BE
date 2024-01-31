package com.yjy.escapezone.controller.request;

import com.yjy.escapezone.domain.room.Location;
import com.yjy.escapezone.domain.room.Thema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@SuppressWarnings("unused")
public class MakeRoomRequest {

    @NotBlank(message = "title 은 필수 값 입니다.")
    private String title;

    @NotBlank(message = "password 은 필수 값 입니다.")
    private String password;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate playDate;

    private Thema thema;

    private Location location;

    @NotNull(message = "maxPlayer은 필수 값이며, 1 이상의 값을 가져야 합니다.")
    @Min(value = 2, message = "maxPlayer은 필수 값이며, 2 이상의 값을 가져야 합니다.")
    private Integer maxPlayer;
}
