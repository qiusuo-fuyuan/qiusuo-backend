package com.qiusuo.messaging.service;


import com.qiusuo.messaging.model.Community;
import com.qiusuo.messaging.model.User;
import com.qiusuo.messaging.model.InstantMessage;

import java.util.List;

public interface CommunityService {
    Community save(Community chatRoom);
    Community findById(String chatRoomId);
    Community join(User joiningUser, Community chatRoom);
    Community leave(User leavingUser, Community chatRoom);
    void sendPublicMessage(InstantMessage instantMessage);
    void sendPrivateMessage(InstantMessage instantMessage);
    List<Community> findAll();
}
