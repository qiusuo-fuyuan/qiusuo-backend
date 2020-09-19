package com.qiusuo.communityservice.graphql.resolver;

import com.qiusuo.communityservice.domain.model.Community;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommunityResolver implements GraphQLResolver<Community> {
    public List<String> tags(Community community) {
        return null;
    }
}
