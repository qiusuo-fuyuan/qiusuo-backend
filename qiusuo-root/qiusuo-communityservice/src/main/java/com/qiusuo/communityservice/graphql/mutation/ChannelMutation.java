package com.qiusuo.communityservice.graphql.mutation;


import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.service.ChannelService;
import com.qiusuo.communityservice.exception.QiuSuoException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class ChannelMutation implements GraphQLMutationResolver {
    private ChannelService channelService;

    public ChannelMutation(ChannelService channelService) {
        this.channelService = channelService;
    }

    public Channel createChannel(CreateChannelInput createChannelInput) throws QiuSuoException {
        return channelService.createChannel(createChannelInput.getName(),createChannelInput.getCommunityId());
    }
}
