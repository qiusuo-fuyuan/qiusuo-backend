package com.qiusuo.communityservice.graphql.mutation;


import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.service.ChannelService;
import com.qiusuo.communityservice.exception.QiuSuoException;
import com.qiusuo.communityservice.graphql.types.CreateChannelInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Secured("ROLE_USER")
@Component
public class ChannelMutation implements GraphQLMutationResolver {
    private ChannelService channelService;

    public ChannelMutation(ChannelService channelService) {
        this.channelService = channelService;
    }

    public Channel createChannel(CreateChannelInput createChannelInput) throws QiuSuoException {
        return channelService.createChannel(createChannelInput.getName(), createChannelInput.getCommunityId());
    }
}
