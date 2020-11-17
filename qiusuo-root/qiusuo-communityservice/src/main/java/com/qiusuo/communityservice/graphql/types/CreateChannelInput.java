package com.qiusuo.communityservice.graphql.types;

import com.qiusuo.communityservice.domain.model.ChannelType;
import lombok.Data;

@Data
public class CreateChannelInput {
    private Long communityId;
    private String name;
    private ChannelType channelType;
}
