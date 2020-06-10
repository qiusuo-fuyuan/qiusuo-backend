package com.qiusuo.messaging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.Date;

@PrimaryKeyClass
public class InstantMessageKey {
    @JsonIgnore
    @PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    public String username;

    @PrimaryKeyColumn(name = "chatRoomId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    public String chatRoomId;

    @PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    public Date date;

    InstantMessageKey(final String username, final String chatRoomId, final Date date) {
        this.username = username;
        this.chatRoomId = chatRoomId;
        this.date = date;
    }
}