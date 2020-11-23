package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.ChannelRepository;
import com.qiusuo.communityservice.domain.repository.CommunityRepository;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import com.qiusuo.communityservice.domain.service.UserService;
import com.qiusuo.communityservice.graphql.types.UserRegistrationInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {
    private UserService userService;

    public UserMutation(UserService userService) {
        this.userService = userService;
    }

    public User registerUser(UserRegistrationInput userRegistrationInput) {
        return null;
    }

    public User setActiveCommunity(String communityId) {
        return userService.setActiveCommunity(communityId);
    }

    public User setActiveChannel(String channelId) {
        return userService.setActiveChannel(channelId);
    }
}
