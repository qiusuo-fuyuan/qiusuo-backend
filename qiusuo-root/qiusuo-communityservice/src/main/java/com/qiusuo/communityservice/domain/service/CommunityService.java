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
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        if (!CollectionUtils.isEmpty(tags)) {
            newCommunity.setCommaSeparatedTags(String.join(", ", tags));
        }
        /*
        TODO
        There is a difference between owner and normal subscribers.
        We need to differentiate that
         */
        User user = userRepository.findUserByUserId(userId);
        Collection<User> subscribedUsers = new ArrayList<>();
        if (user == null) {
            LOGGER.error("CreateCommunity: user with userId {} does not exist", userId);
            throw new QiuSuoException("creating community failed by userId {}", userId);
        } else {
            subscribedUsers.add(user);
        }
        newCommunity.setSubscribedUsers(subscribedUsers);

        List<Channel> defaultChannels = createDefaultChannels();
        newCommunity.setChannels(defaultChannels);
        /*Is this correct, first save, then return.What if save throws
        exceptions.Even it throws exception, it is still runtime exception
         */
        communityRepository.save(newCommunity);

        //set the newly created community as the current active community at the same time.
        user.setActiveCommunity(newCommunity);
        user.setActiveChannel(defaultChannels.get(0));
        userRepository.save(user);
        return newCommunity;
    }


    public Community getActiveCommunityForUser(String userId) {
        User user = userRepository.findUserByUserId(userId);
        return user.getActiveCommunity();
    }

    private List<Channel> createDefaultChannels() {
        List<Channel> channels = new ArrayList<>() {
            {
                add(new Channel(Constants.CHANNEL_QA_NAME));
            }
        };
        return channels;
    }


}
