package com.qiusuo.communityservice.graphql.mutation;


import com.qiusuo.communityservice.domain.service.ChannelService;
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

    public String test() {
        return "";
    }
}
