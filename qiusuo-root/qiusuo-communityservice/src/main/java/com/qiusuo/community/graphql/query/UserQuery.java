package com.qiusuo.community.graphql.query;

import com.qiusuo.community.domain.model.User;
import com.qiusuo.community.domain.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

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
}
