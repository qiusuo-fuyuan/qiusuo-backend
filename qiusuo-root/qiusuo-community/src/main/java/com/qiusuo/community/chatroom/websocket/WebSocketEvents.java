package com.qiusuo.community.chatroom.websocket;

import com.qiusuo.community.chatroom.model.CommunityUser;
import com.qiusuo.community.chatroom.service.CommunityRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEvents {

    @Autowired
    private CommunityRoomService communityRoomService;

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String chatRoomId = headers.getNativeHeader("chatRoomId").get(0);
        headers.getSessionAttributes().put("chatRoomId", chatRoomId);
        CommunityUser joiningUser = new CommunityUser(event.getUser().getName());

        communityRoomService.join(joiningUser, communityRoomService.findById(chatRoomId));
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String chatRoomId = headers.getSessionAttributes().get("chatRoomId").toString();
        CommunityUser leavingUser = new CommunityUser(event.getUser().getName());

        communityRoomService.leave(leavingUser, communityRoomService.findById(chatRoomId));
    }
}
