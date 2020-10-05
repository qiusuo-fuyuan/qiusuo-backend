package com.qiusuo.communityservice.graphql.query;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.service.CommunityService;
import com.qiusuo.communityservice.domain.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class CommunityQuery implements GraphQLQueryResolver {
    private UserService userService;
    private CommunityService communityService;

    public CommunityQuery(UserService userService, CommunityService communityService) {
        this.userService = userService;
        this.communityService = communityService;
    }

    public Community activeCommunity(String userId) {
        //We will always set active community for the user when he subscribes some community
        return communityService.getActiveCommunityForUserId(userId);
    }
}
