package com.qiusuo.community.chatroom.repository;

import com.qiusuo.community.chatroom.model.InstantMessage;
import com.qiusuo.community.chatroom.model.InstantMessageKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface InstantMessageRepository extends CassandraRepository<InstantMessage, InstantMessageKey> {

    List<InstantMessage> findInstantMessagesByUsernameAndChatRoomId(String username, String chatRoomId);
}
