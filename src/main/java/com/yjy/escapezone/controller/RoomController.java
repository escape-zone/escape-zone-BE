package com.yjy.escapezone.controller;

import com.yjy.escapezone.common.dto.ApiResponse;
import com.yjy.escapezone.controller.request.MakeRoomRequest;
import com.yjy.escapezone.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/room", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {

    private final RoomService roomService;
    /**
     * 방만들기
     */
    @PostMapping
    public ApiResponse<?> makeRoom(@RequestBody MakeRoomRequest request, Authentication authentication) {
        String email = authentication.getName();
        roomService.makeRoom(request, email);
        return null;
    }

}
