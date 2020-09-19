package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.service.CommunityService;
import com.qiusuo.communityservice.exception.QiuSuoException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class CommunityMutation implements GraphQLMutationResolver {
    private CommunityService communityService;

    public CommunityMutation(CommunityService communityService) {
        this.communityService = communityService;
    }
    public Community createCommunity(CreateCommunityInput createCommunityInput) throws QiuSuoException {
        return this.communityService.createCommunity(
                createCommunityInput.getUserId(),
                createCommunityInput.getTitle(),
                createCommunityInput.getDescription(),
                createCommunityInput.getAvatarUrl());
    }

}
