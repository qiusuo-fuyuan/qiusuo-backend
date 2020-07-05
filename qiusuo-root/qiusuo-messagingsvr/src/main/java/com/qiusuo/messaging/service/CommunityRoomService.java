package com.qiusuo.messaging.service;


import com.qiusuo.messaging.model.CommunityRoom;
import com.qiusuo.messaging.model.CommunityUser;
import com.qiusuo.messaging.model.InstantMessage;

import java.util.List;

public interface CommunityRoomService {

    CommunityRoom save(CommunityRoom chatRoom);
    CommunityRoom findById(String chatRoomId);
    CommunityRoom join(CommunityUser joiningUser, CommunityRoom chatRoom);
    CommunityRoom leave(CommunityUser leavingUser, CommunityRoom chatRoom);
    void sendPublicMessage(InstantMessage instantMessage);
    void sendPrivateMessage(InstantMessage instantMessage);
    List<CommunityRoom> findAll();
}
