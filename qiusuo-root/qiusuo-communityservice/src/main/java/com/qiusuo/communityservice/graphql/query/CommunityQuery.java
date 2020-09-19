package com.qiusuo.communityservice.graphql.query;

import com.qiusuo.communityservice.domain.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class CommunityQuery implements GraphQLQueryResolver {
    private UserService userService;
}
