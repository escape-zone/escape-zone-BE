package com.yjy.escapezone.controller.request;

import com.yjy.escapezone.domain.room.Location;
import com.yjy.escapezone.domain.room.Thema;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class MakeRoomRequest {

    private String title;

    private String password;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate playDate;

    private Thema thema;

    private Location location;

    private int maxPlayer;
}
