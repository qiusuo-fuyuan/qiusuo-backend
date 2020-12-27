package com.qiusuo.communityservice.domain.service;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.ChannelRepository;
import com.qiusuo.communityservice.domain.repository.CommunityRepository;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import com.qiusuo.communityservice.exception.QiuSuoException;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ChannelService {
    private static Logger LOGGER = LoggerFactory.getLogger(ChannelService.class);
    private ChannelRepository channelRepository;
    private CommunityRepository communityRepository;
    private UserRepository userRepository;
    private UserService userService;

    public ChannelService(ChannelRepository channelRepository, CommunityRepository communityRepository,
                          UserService userService, UserRepository userRepository) {
        this.channelRepository = channelRepository;
        this.communityRepository = communityRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public Channel createChannel(String name, Long communityId) throws QiuSuoException {
        Community community = communityRepository.findById(communityId).get();
        User user = userService.getCurrentUser();
        if (community == null) {
            LOGGER.error("CreateChannel: parent communityId {} does not exist", communityId);
            throw new QiuSuoException("CreateChannel: failed, commmunity id {} does not exist", communityId);
        }

        Channel channel = new Channel();
        channel.setName(name);
        channel.setCommunity(community);

        //run time error might be thrown from here
        channelRepository.save(channel);
        user.setActiveChannels(replaceActiveChannelForCommunity(user.getActiveChannels(), community, channel));
        userRepository.save(user);
        Hibernate.initialize(community.getChannels());
        return channel;
    }

    public Channel setActiveChannel(String channelId) {
        User user = userService.getCurrentUser();
        Community activeCommunity = user.getActiveCommunity();
        Channel channel = channelRepository.getOne(Long.parseLong(channelId));
        user.setActiveChannels(replaceActiveChannelForCommunity(user.getActiveChannels(), activeCommunity, channel));
        userRepository.save(user);
        Hibernate.initialize(channel);
        return channel;
    }

    private Collection<Channel> replaceActiveChannelForCommunity(Collection<Channel> currentActiveChannels,
                                                                 Community activeCommunity, Channel newActiveChannel) {
        List<Channel> updatedActiveChannel = currentActiveChannels.stream()
                .filter(channel -> !channel.getCommunity().getId().equals(activeCommunity.getId()))
                .collect(Collectors.toList());
        updatedActiveChannel.add(newActiveChannel);
        return updatedActiveChannel;
    }

    public Channel getActiveChannelForCommunity() {
        User user = userService.getCurrentUser();
        Community activeCommunity = user.getActiveCommunity();
        return user.getActiveChannels().stream()
                .filter(channel -> channel.getCommunity().getId().equals(activeCommunity.getId()))
                .findFirst().orElse(null);
    }
}
