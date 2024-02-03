package com.yjy.escapezone.domain.room;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String hostId;

    @Column(length = 100, nullable = false)
    private String password;

    private LocalDateTime createDate;
    
    private LocalDateTime playDate;

    @Convert(converter = Thema.ThemaConverter.class)
    private Thema thema;

    @Convert(converter = Location.LocationConverter.class)
    private Location location;

    private Integer maxPlayer;

    @Convert(converter = RoomStatus.RoomStatusConverter.class)
    private RoomStatus roomStatus;

    private LocalDateTime signDate;

    private Long categoryId;

}
