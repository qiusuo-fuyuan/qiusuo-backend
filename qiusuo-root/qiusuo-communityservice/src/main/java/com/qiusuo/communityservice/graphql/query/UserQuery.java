package com.qiusuo.communityservice.graphql.query;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Secured("ROLE_USER")
@Component
public class UserQuery implements GraphQLQueryResolver {
    UserService userService;

    public UserQuery(UserService userService) {
        this.userService = userService;
    }

    //We could actually get the user details information from the JWT token
    public User userDetails() throws InterruptedException {
        return userService.getCurrentUser();
    }

    public Collection<Community> myCommunities(String userId) {
        return userService.getCommunitiesForUserId(userId);
    }
}
