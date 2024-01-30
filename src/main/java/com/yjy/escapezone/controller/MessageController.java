package com.yjy.escapezone.controller;

import com.yjy.escapezone.controller.request.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    // 새로운 사용자가 웹소켓 연결 시 실행
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        log.info("Received a new web socket connection");
    }

    // 사용자가 웹 소켓 연결을 끊으면 실행됨
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccesor.getSessionId();
        log.info("sessionId Disconnected : " + sessionId);
    }


    // /pub/cache 로 메시지를 발행한다.
    @MessageMapping("/chat")
    @SendTo("/sub/chat")
    public void sendMessage(MessageDto message) {
        if (MessageDto.MessageType.ENTER.equals(message.getMessageType())){
            message.setMessage(message.getSenderId() + "님이 입장하셨습니다.");
        }
        // /sub/cache 에 구독중인 client에 메세지를 보낸다.
        sendingOperations.convertAndSend("/sub/chat/" + message.getChatRoomId(), message);
    }

}
