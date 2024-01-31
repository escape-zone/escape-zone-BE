package com.yjy.escapezone.controller;

import com.yjy.escapezone.controller.request.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat")
    @SendTo("/sub/chat")
    public void sendMessage(MessageDto message) {
        if (MessageDto.MessageType.ENTER.equals(message.getMessageType())){
            message.setMessage(message.getSenderId() + "님이 입장하셨습니다.");
        }
        sendingOperations.convertAndSend("/sub/chat/" + message.getChatRoomId(), message);
    }

}
