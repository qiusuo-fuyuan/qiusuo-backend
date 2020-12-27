package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.service.ChannelService;
import com.qiusuo.communityservice.domain.service.CommunityService;
import com.qiusuo.communityservice.domain.service.UserService;
import com.qiusuo.communityservice.graphql.types.UserRegistrationInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {
    private UserService userService;
    private ChannelService channelService;
    private CommunityService communityService;

    public UserMutation(UserService userService, ChannelService channelService,
                        CommunityService communityService) {
        this.userService = userService;
        this.channelService = channelService;
        this.communityService = communityService;
    }

    public User registerUser(UserRegistrationInput userRegistrationInput) {
        return null;
    }

    public Community setActiveCommunity(String communityId) {
        return communityService.setActiveCommunity(communityId);
    }

    public Channel setActiveChannel(String channelId) {
        return channelService.setActiveChannel(channelId);
    }
}
