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

    /**
     * TODO: we called the userService two times in the resolver(another in CommunityResovler)
     * Our transactional annotation is put in service layer. This is not able to use
     * the session cache provided by Hibernate
     *
     * @param channel
     * @return
     */
    public boolean active(Channel channel) {
        return userService.getCurrentUser().getActiveChannel().getId().equals(channel.getId());
    }

    public Community parentCommunity(Channel channel) {
        return channel.getCommunity();
    }
}
