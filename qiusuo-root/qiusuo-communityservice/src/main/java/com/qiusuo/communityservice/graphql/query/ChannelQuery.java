package com.qiusuo.communityservice.graphql.query;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.service.ChannelService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class ChannelQuery implements GraphQLQueryResolver {
    private ChannelService channelService;

    public ChannelQuery(ChannelService channelService) {
        this.channelService = channelService;
    }

    public Channel activeChannel() {
        return channelService.getActiveChannelForCommunity();
    }
}
