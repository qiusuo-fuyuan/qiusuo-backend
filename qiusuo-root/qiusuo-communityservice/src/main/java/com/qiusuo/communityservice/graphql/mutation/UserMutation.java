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
    private CommunityRepository communityRepository;
    private ChannelRepository channelRepository;
    private UserService userService;
    private UserRepository userRepository;

    public UserMutation(UserService userService, CommunityRepository communityRepository,
                        ChannelRepository channelRepository, UserRepository userRepository) {
        this.userService = userService;
        this.communityRepository = communityRepository;
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegistrationInput userRegistrationInput) {
        return null;
    }

    public User setActiveCommunity(String communityId) {
        User user = userService.getCurrentUser();
        Community community = communityRepository.getOne(Long.parseLong(communityId));
        user.setActiveCommunity(community);
        userRepository.save(user);
        return user;
    }

    public User setActiveChannel(String channelId) {
        User user = userService.getCurrentUser();
        Channel channel = channelRepository.getOne(Long.parseLong(channelId));
        user.setActiveChannel(channel);
        userRepository.save(user);
        return user;
    }
}
