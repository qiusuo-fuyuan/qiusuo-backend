package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.ChannelRepository;
import com.qiusuo.communityservice.domain.repository.CommunityRepository;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import com.qiusuo.communityservice.graphql.types.UserRegistrationInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {
    private UserRepository userRepository;
    private CommunityRepository communityRepository;
    private ChannelRepository channelRepository;

    public UserMutation(UserRepository userRepository, CommunityRepository communityRepository,
                        ChannelRepository channelRepository) {
        this.userRepository = userRepository;
        this.communityRepository = communityRepository;
        this.channelRepository = channelRepository;
    }

    public User registerUser(UserRegistrationInput userRegistrationInput) {
        return null;
    }

    public User setActiveCommunity(String communityId, String userId) {
        User user = userRepository.findUserByUserId(userId);
        Community community = communityRepository.getOne(Long.parseLong(communityId));
        user.setActiveCommunity(community);
        userRepository.save(user);
        return user;
    }

    public User setActiveChannel(String channelId, String userId) {
        User user = userRepository.findUserByUserId(userId);
        Channel channel = channelRepository.getOne(Long.parseLong(channelId));
        user.setActiveChannel(channel);
        userRepository.save(user);
        return user;
    }
}
