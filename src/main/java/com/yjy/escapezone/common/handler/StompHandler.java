package com.yjy.escapezone.common.handler;

import com.yjy.escapezone.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class StompHandler implements ChannelInterceptor {
    private final TokenProvider tokenProvider;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("Stomp Handler 실행");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            handleConnect(sessionId, accessor);
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            handleDisconnect(sessionId);
        }
        return message;
    }

    private void handleConnect(String sessionId, StompHeaderAccessor accessor) {
        log.debug("세션 들어옴 => {}", sessionId);
        if(!tokenProvider.validateToken(accessor.getFirstNativeHeader("Authorization")))
            throw new AccessDeniedException("");
    }

    private void handleDisconnect(String sessionId) {
        log.debug("세션 끊음 => {}", sessionId);
    }
}
