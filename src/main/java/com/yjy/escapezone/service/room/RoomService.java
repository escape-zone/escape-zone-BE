package com.yjy.escapezone.service.room;

import com.yjy.escapezone.controller.request.MakeRoomRequest;
import com.yjy.escapezone.domain.room.Room;
import com.yjy.escapezone.domain.room.RoomStatus;
import com.yjy.escapezone.domain.user.User;
import com.yjy.escapezone.domain.userroom.UserRoom;
import com.yjy.escapezone.repository.room.RoomRepository;
import com.yjy.escapezone.repository.user.UserRepository;
import com.yjy.escapezone.repository.userroom.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final UserRepository userRepository;


    public Long makeRoom(MakeRoomRequest request, String email) {
        User currentUser = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("사용자 정보가 없습니다."));

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

        UserRoom userRoom = UserRoom.builder().user(currentUser).room(room).build();
        userRoomRepository.save(userRoom);

        return room.getId();
    }
}
