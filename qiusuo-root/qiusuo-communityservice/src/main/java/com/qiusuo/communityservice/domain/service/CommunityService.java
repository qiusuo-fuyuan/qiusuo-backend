package com.qiusuo.communityservice.domain.service;


import com.qiusuo.communityservice.constants.Constants;
import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.CommunityRepository;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import com.qiusuo.communityservice.exception.QiuSuoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CommunityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityService.class);
    private CommunityRepository communityRepository;
    private UserRepository userRepository;

    public CommunityService(CommunityRepository communityRepository, UserRepository userRepository) {
        this.communityRepository = communityRepository;
        this.userRepository = userRepository;
    }

    public Community createCommunity(String userId,
                                     String title,
                                     String description,
                                     String avatarUrl) throws QiuSuoException {

        Community newCommunity = new Community();
        newCommunity.setTitle(title);
        newCommunity.setDescription(description);
        newCommunity.setAvatarUrl(avatarUrl);
        /*
        TODO
        There is a difference between owner and normal subscribers.
        We need to differentiate that
         */
        User owner = userRepository.findUserByUserId(userId);
        Collection<User> subscribedUsers = new ArrayList<>();
        if (owner == null) {
            LOGGER.error("creating community: user with userId {} does not exist",userId);
            throw new QiuSuoException("creating community failed");
        } else {
            subscribedUsers.add(owner);
        }
        newCommunity.setSubscribedUsers(subscribedUsers);
        newCommunity.setChannels(createDefaultChannels());
        /*Is this correct, first save, then return.What if save throws
        exceptions.Even it throws exception, it is still runtime exception
         */
        communityRepository.save(newCommunity);
        return newCommunity;
    }


    private Collection<Channel> createDefaultChannels() {
        Collection<Channel> channels = new ArrayList<>() {
            {
                add(new Channel(Constants.CHANNEL_QA_NAME));
            }
        };
        return channels;
    }
}
