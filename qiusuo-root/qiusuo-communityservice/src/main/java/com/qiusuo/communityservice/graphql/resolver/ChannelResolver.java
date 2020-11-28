package com.qiusuo.communityservice.graphql.resolver;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.service.ChannelService;
import com.qiusuo.communityservice.domain.service.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ChannelResolver implements GraphQLResolver<Channel> {
    private UserService userService;
    private ChannelService channelService;

    public ChannelResolver(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    public Community parentCommunity(Channel channel) {
        return channel.getCommunity();
    }
}
