package com.qiusuo.messaging.service;

import com.qiusuo.messaging.model.Community;
import com.qiusuo.messaging.model.User;
import com.qiusuo.messaging.model.InstantMessage;
import com.qiusuo.messaging.repository.CommunityRepository;
import com.qiusuo.messaging.utils.Destinations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisCommunityService implements CommunityService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private InstantMessageService instantMessageService;

    @Override
    public Community save(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community findById(String chatRoomId) {
        return communityRepository.findById(chatRoomId).get();
    }

    @Override
    public Community join(User joiningUser, Community community) {
        community.addUser(joiningUser);
        communityRepository.save(community);

        //sendPublicMessage(SystemMessages.welcome(chatRoom.getId(), joiningUser.getUsername()));
        updateConnectedUsersViaWebSocket(community);
        return community;
    }

    @Override
    public Community leave(User leavingUser, Community community) {
        //sendPublicMessage(SystemMessages.goodbye(chatRoom.getId(), leavingUser.getUsername()));

        community.removeUser(leavingUser);
        communityRepository.save(community);

        updateConnectedUsersViaWebSocket(community);
        return community;
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
    public List<Community> findAll() {
        return (List<Community>) communityRepository.findAll();
    }

    private void updateConnectedUsersViaWebSocket(Community chatRoom) {
        webSocketMessagingTemplate.convertAndSend(
                Destinations.ChatRoom.connectedUsers(chatRoom.getId()),
                chatRoom.getConnectedUsers());
    }
}
