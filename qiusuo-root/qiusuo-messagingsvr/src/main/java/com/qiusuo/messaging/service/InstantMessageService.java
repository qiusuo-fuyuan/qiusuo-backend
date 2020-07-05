package com.qiusuo.messaging.service;

import com.qiusuo.messaging.model.InstantMessage;

import java.util.List;

public interface InstantMessageService {

    void appendInstantMessageToConversations(InstantMessage instantMessage);
    List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId);
}
