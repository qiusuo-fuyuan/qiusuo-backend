package com.qiusuo.communityservice.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * TODO:
 */
@Data
@RequiredArgsConstructor
@Entity
public class Channel {
    @Id
    @GeneratedValue(generator = "channel_id_generator")
    @SequenceGenerator(name = "channel_id_generator", sequenceName = "community_seq")

    private Long id;
    private String name;

    @ManyToOne
    private Community community;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    public Channel(String name) {
        this.name = name;
    }


}
