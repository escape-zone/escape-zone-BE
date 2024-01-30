package com.yjy.escapezone.repository.room;

import com.yjy.escapezone.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room save(Room room);

}
