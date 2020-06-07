package com.qiusuo.community.chat.service;

import com.qiusuo.community.chat.model.CommunityRoom;
import com.qiusuo.community.chat.model.InstantMessage;
import com.qiusuo.community.chat.repository.InstantMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CassandraInstantMessageService implements InstantMessageService {

    @Autowired
    private InstantMessageRepository instantMessageRepository;

    @Autowired
    private CommunityRoomService communityRoomService;

    @Override
    public void appendInstantMessageToConversations(InstantMessage instantMessage) {
        if (instantMessage.isFromAdmin() || instantMessage.isPublic()) {
            CommunityRoom communityRoom = communityRoomService.findById(instantMessage.getChatRoomId());

            communityRoom.getConnectedUsers().forEach(connectedUser -> {
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
