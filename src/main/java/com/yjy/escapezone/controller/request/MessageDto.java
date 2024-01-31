package com.yjy.escapezone.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageDto {

    public enum MessageType{
        ENTER, TALK
    }
    private MessageType messageType;
    private Long chatRoomId;
    private Long senderId;
    private String message;
}
