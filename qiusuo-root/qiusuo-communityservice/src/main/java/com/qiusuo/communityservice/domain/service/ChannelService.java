package com.qiusuo.communityservice.domain.service;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.repository.ChannelRepository;
import com.qiusuo.communityservice.domain.repository.CommunityRepository;
import com.qiusuo.communityservice.exception.QiuSuoException;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ChannelService {
    private static Logger LOGGER = LoggerFactory.getLogger(ChannelService.class);
    private ChannelRepository channelRepository;
    private CommunityRepository communityRepository;
    private UserService userService;

    public ChannelService(ChannelRepository channelRepository, CommunityRepository communityRepository,
                          UserService userService) {
        this.channelRepository = channelRepository;
        this.communityRepository = communityRepository;
        this.userService = userService;
    }

    public Community createChannel(String name, Long communityId) throws QiuSuoException {
        Community community = communityRepository.findById(communityId).get();
        if (community == null) {
            LOGGER.error("CreateChannel: parent communityId {} does not exist", communityId);
            throw new QiuSuoException("CreateChannel: failed, commmunity id {} does not exist", communityId);
        }

        Channel channel = new Channel();
        channel.setName(name);
        channel.setCommunity(community);

        //run time error might be thrown from here
        channelRepository.save(channel);
        Hibernate.initialize(community.getChannels());
        return community;
    }

    public Channel getActiveChannelForCurrentUser() {
        return userService.getCurrentUser().getActiveChannel();
    }
}
