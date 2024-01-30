package com.yjy.escapezone.service.room;

import com.yjy.escapezone.controller.request.MakeRoomRequest;
import com.yjy.escapezone.domain.room.Room;
import com.yjy.escapezone.domain.room.RoomStatus;
import com.yjy.escapezone.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Transactional
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    public void makeRoom(MakeRoomRequest request, String email) {
        Room room = Room.builder()
                .title(request.getTitle())
                .hostId(email)
                .password(request.getPassword())
                .createDate(LocalDateTime.now())
                .playDate(request.getPlayDate().atStartOfDay())
                .thema(request.getThema())
                .location(request.getLocation())
                .roomStatus(RoomStatus.WAITING)
                .maxPlayer(request.getMaxPlayer()).build();
        roomRepository.save(room);
    }
}
