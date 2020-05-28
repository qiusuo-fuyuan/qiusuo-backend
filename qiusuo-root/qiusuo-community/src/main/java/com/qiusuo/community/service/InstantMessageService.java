package com.qiusuo.community.service;

import com.qiusuo.community.model.InstantMessage;

import java.util.List;

public interface InstantMessageService {

    void appendInstantMessageToConversations(InstantMessage instantMessage);
    List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId);
}
