package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.service.CommunityService;
import com.qiusuo.communityservice.domain.service.UserService;
import com.qiusuo.communityservice.exception.QiuSuoException;
import com.qiusuo.communityservice.graphql.types.CreateCommunityInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class CommunityMutation implements GraphQLMutationResolver {
    private CommunityService communityService;
    private UserService userService;

    public CommunityMutation(CommunityService communityService, UserService userService) {
        this.communityService = communityService;
        this.userService = userService;
    }

    public Community createCommunity(CreateCommunityInput createCommunityInput) throws QiuSuoException {
        return this.communityService.createCommunity(
                createCommunityInput.getOwnerId(),
                createCommunityInput.getTitle(),
                createCommunityInput.getDescription(),
                createCommunityInput.getAvatarUrl(), createCommunityInput.getTags());
    }
}
