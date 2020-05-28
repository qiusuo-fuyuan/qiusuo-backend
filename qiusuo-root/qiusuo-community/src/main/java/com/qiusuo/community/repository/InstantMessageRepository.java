package com.qiusuo.community.repository;

import com.qiusuo.community.model.InstantMessage;
import com.qiusuo.community.model.InstantMessageKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface InstantMessageRepository extends CassandraRepository<InstantMessage, InstantMessageKey> {

    List<InstantMessage> findInstantMessagesByUsernameAndChatRoomId(String username, String chatRoomId);
}
