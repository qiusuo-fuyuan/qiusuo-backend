package com.qiusuo.communityservice.graphql.resolver;

import com.qiusuo.communityservice.domain.model.Community;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommunityResolver implements GraphQLResolver<Community> {
    public List<String> tags(Community community) {
        return Arrays.stream(community.getCommaSeparatedTags()
                .split(","))
                .collect(Collectors.toList());
    }
}
