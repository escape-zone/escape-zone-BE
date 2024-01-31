package com.yjy.escapezone.common.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class StompHandler implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("Stomp Handler 실행");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            handleConnect(sessionId);
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            handleDisconnect(sessionId);
        }
        return message;
    }

    private void handleConnect(String sessionId) {
        log.debug("세션 들어옴 => {}", sessionId);
    }

    private void handleDisconnect(String sessionId) {
        log.debug("세션 끊음 => {}", sessionId);
    }
}
