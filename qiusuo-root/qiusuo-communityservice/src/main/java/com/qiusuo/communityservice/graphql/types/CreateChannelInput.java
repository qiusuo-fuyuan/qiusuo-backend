package com.qiusuo.communityservice.graphql.types;

import com.qiusuo.communityservice.graphql.types.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelInput {
    private Long communityId;
    private String name;
    private ChannelType channelType;
}
