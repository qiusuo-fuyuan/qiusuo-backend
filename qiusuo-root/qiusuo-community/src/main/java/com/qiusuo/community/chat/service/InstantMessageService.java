package com.qiusuo.community.chat.service;

import com.qiusuo.community.chat.model.InstantMessage;

import java.util.List;

public interface InstantMessageService {

    void appendInstantMessageToConversations(InstantMessage instantMessage);
    List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId);
}
