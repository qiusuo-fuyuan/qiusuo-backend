package com.qiusuo.community.chatroom.service;

import com.qiusuo.community.chatroom.model.InstantMessage;

import java.util.List;

public interface InstantMessageService {

    void appendInstantMessageToConversations(InstantMessage instantMessage);
    List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId);
}
