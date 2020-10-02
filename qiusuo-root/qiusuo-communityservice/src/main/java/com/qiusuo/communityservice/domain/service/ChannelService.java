package com.qiusuo.communityservice.domain.service;

import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.repository.ChannelRepository;
import com.qiusuo.communityservice.domain.repository.CommunityRepository;
import com.qiusuo.communityservice.exception.QiuSuoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {
    private static Logger LOGGER = LoggerFactory.getLogger(ChannelService.class);
    private ChannelRepository channelRepository;
    private CommunityRepository communityRepository;

    public ChannelService(ChannelRepository channelRepository, CommunityRepository communityRepository) {
        this.channelRepository = channelRepository;
        this.communityRepository = communityRepository;
    }

    public Channel createChannel(String name, Long communityId) throws QiuSuoException {
        Optional<Community> community = communityRepository.findById(communityId);
        if(!community.isPresent()) {
            LOGGER.error("CreateChannel: parent communityId {} does not exist",communityId);
            throw new QiuSuoException("CreateChannel: failed, commmunity id {} does not exist", communityId);
        }

        Channel channel = new Channel();
        channel.setName(name);
        channel.setCommunity(community.get());

        //run time error might be thrown from here
        channelRepository.save(channel);
        return channel;
    }
}
