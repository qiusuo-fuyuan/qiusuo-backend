package com.qiusuo.messaging.event;

import com.qiusuo.messaging.model.User;
import com.qiusuo.messaging.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEvents {

    @Autowired
    private CommunityService communityService;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String chatRoomId = headers.getNativeHeader("chatRoomId").get(0);
        headers.getSessionAttributes().put("chatRoomId", chatRoomId);
        User joiningUser = new User(event.getUser().getName());

        communityService.join(joiningUser, communityService.findById(chatRoomId));
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String chatRoomId = headers.getSessionAttributes().get("chatRoomId").toString();
        User leavingUser = new User(event.getUser().getName());

        communityService.leave(leavingUser, communityService.findById(chatRoomId));
    }
}
