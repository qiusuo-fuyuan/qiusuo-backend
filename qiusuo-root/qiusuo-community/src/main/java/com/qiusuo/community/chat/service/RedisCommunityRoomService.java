package com.qiusuo.community.chat.service;


import com.qiusuo.community.chat.model.CommunityUser;
import com.qiusuo.community.chat.model.InstantMessage;
import com.qiusuo.community.chat.repository.CommunityRoomRepository;
import com.qiusuo.community.chat.utils.Destinations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.qiusuo.community.chat.model.CommunityRoom;

import java.util.List;

@Service
public class RedisCommunityRoomService implements CommunityRoomService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private CommunityRoomRepository communityRoomRepository;

    @Autowired
    private InstantMessageService instantMessageService;

    @Override
    public CommunityRoom save(CommunityRoom communityRoom) {
        return communityRoomRepository.save(communityRoom);
    }

    @Override
    public CommunityRoom findById(String chatRoomId) {
        return communityRoomRepository.findById(chatRoomId).get();
    }

    @Override
    public CommunityRoom join(CommunityUser joiningUser, CommunityRoom communityRoom) {
        communityRoom.addUser(joiningUser);
        communityRoomRepository.save(communityRoom);

        //sendPublicMessage(SystemMessages.welcome(chatRoom.getId(), joiningUser.getUsername()));
        updateConnectedUsersViaWebSocket(communityRoom);
        return communityRoom;
    }

    @Override
    public CommunityRoom leave(CommunityUser leavingUser, CommunityRoom communityRoom) {
        //sendPublicMessage(SystemMessages.goodbye(chatRoom.getId(), leavingUser.getUsername()));

        communityRoom.removeUser(leavingUser);
        communityRoomRepository.save(communityRoom);

        updateConnectedUsersViaWebSocket(communityRoom);
        return communityRoom;
    }

    @Override
    public void sendPublicMessage(InstantMessage instantMessage) {
        webSocketMessagingTemplate.convertAndSend(
                Destinations.ChatRoom.publicMessages(instantMessage.getChatRoomId()),
                instantMessage);

        instantMessageService.appendInstantMessageToConversations(instantMessage);
    }

    @Override
    public void sendPrivateMessage(InstantMessage instantMessage) {
        webSocketMessagingTemplate.convertAndSendToUser(
                instantMessage.getToUser(),
                Destinations.ChatRoom.privateMessages(instantMessage.getChatRoomId()),
                instantMessage);

        webSocketMessagingTemplate.convertAndSendToUser(
                instantMessage.getFromUser(),
                Destinations.ChatRoom.privateMessages(instantMessage.getChatRoomId()),
                instantMessage);

        instantMessageService.appendInstantMessageToConversations(instantMessage);
    }

    @Override
    public List<CommunityRoom> findAll() {
        return (List<CommunityRoom>) communityRoomRepository.findAll();
    }

    private void updateConnectedUsersViaWebSocket(CommunityRoom chatRoom) {
        webSocketMessagingTemplate.convertAndSend(
                Destinations.ChatRoom.connectedUsers(chatRoom.getId()),
                chatRoom.getConnectedUsers());
    }
}
