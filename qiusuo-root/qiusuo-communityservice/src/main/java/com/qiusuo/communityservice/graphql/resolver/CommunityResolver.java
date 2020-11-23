package com.qiusuo.communityservice.graphql.resolver;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.service.UserService;
import graphql.kickstart.tools.GraphQLResolver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class CommunityResolver implements GraphQLResolver<Community> {
    private UserService userService;

    public CommunityResolver(UserService userService) {
        this.userService = userService;
    }

    public List<String> tags(Community community) {
        if (StringUtils.isBlank(community.getCommaSeparatedTags())) {
            return null;
        }
        return Arrays.stream(community.getCommaSeparatedTags()
                .split(","))
                .collect(Collectors.toList());
    }
}
