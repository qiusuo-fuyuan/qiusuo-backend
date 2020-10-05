package com.qiusuo.communityservice.domain.service;


import com.qiusuo.communityservice.constants.Constants;
import com.qiusuo.communityservice.domain.model.Channel;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.CommunityRepository;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import com.qiusuo.communityservice.exception.QiuSuoException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
                                     String avatarUrl,
    List<String> tags) throws QiuSuoException {

        Community newCommunity = new Community();
        newCommunity.setTitle(title);
        newCommunity.setDescription(description);
        newCommunity.setAvatarUrl(avatarUrl);
        if(!CollectionUtils.isEmpty(tags)) {
            newCommunity.setCommaSeparatedTags(String.join(", ", tags));
        }
        /*
        TODO
        There is a difference between owner and normal subscribers.
        We need to differentiate that
         */
        User owner = userRepository.findUserByUserId(userId);
        Collection<User> subscribedUsers = new ArrayList<>();
        if (owner == null) {
            LOGGER.error("CreateCommunity: user with userId {} does not exist",userId);
            throw new QiuSuoException("creating community failed by userId {}", userId);
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

    public Community getActiveCommunityForUserId(String userId) {
        User user = userRepository.findUserByUserId(userId);

        //TODO: currently we always return the first one as the active community
        return user.getSubscribedCommunities().iterator().next();
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
