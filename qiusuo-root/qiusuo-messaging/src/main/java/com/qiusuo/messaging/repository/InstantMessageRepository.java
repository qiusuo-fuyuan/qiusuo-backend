package com.qiusuo.messaging.repository;

import com.qiusuo.community.chat.model.InstantMessage;
import com.qiusuo.community.chat.model.InstantMessageKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface InstantMessageRepository extends CassandraRepository<InstantMessage, InstantMessageKey> {

    List<InstantMessage> findInstantMessagesByUsernameAndChatRoomId(String username, String chatRoomId);
}
