package com.qiusuo.communityservice.domain.model;

import com.qiusuo.communityservice.graphql.types.ChannelType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
public class Channel {
    @Id
    @GeneratedValue(generator = "channel_id_generator")
    @SequenceGenerator(name = "channel_id_generator", sequenceName = "community_seq")

    private Long id;
    private String name;
    private ChannelType type;

    @ManyToOne
    private Community community;

    public Channel(String name) {
        this.name = name;
    }
}
