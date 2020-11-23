package com.qiusuo.communityservice.graphql.resolver;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.service.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ChannelResolver implements GraphQLResolver<Channel> {
    private UserService userService;

    public ChannelResolver(UserService userService) {
        this.userService = userService;
    }


    public Community parentCommunity(Channel channel) {
        return channel.getCommunity();
    }
}
