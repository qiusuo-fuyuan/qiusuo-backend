package com.qiusuo.messaging.service;


import com.qiusuo.messaging.model.Community;
import com.qiusuo.messaging.model.InstantMessage;
import com.qiusuo.messaging.repository.InstantMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CassandraInstantMessageService implements InstantMessageService {
    private InstantMessageRepository instantMessageRepository;
    private CommunityService communityService;

    public CassandraInstantMessageService(InstantMessageRepository instantMessageRepository,
                                          CommunityService communityService) {
        this.instantMessageRepository = instantMessageRepository;
        this.communityService = communityService;
    }

    @Override
    public void appendInstantMessageToConversations(InstantMessage instantMessage) {
        if (instantMessage.isFromAdmin() || instantMessage.isPublic()) {
            Community community = communityService.findById(instantMessage.getChatRoomId());

            community.getConnectedUsers().forEach(connectedUser -> {
                instantMessage.setUsername(connectedUser.getUsername());
                instantMessageRepository.save(instantMessage);
            });
        } else {
            instantMessage.setUsername(instantMessage.getFromUser());
            instantMessageRepository.save(instantMessage);

            instantMessage.setUsername(instantMessage.getToUser());
            instantMessageRepository.save(instantMessage);
        }
    }

    @Override
    public List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId) {
        return instantMessageRepository.findInstantMessagesByUsernameAndChatRoomId(username, chatRoomId);
    }
}
