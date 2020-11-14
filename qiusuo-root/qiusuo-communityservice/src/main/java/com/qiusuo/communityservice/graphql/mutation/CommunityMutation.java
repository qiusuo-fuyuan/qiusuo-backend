package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.service.CommunityService;
import com.qiusuo.communityservice.exception.QiuSuoException;
import com.qiusuo.communityservice.graphql.types.CreateCommunityInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.Setter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

@Secured("ROLE_USER")
@Component
public class CommunityMutation implements GraphQLMutationResolver {
    @Setter
    private CommunityService communityService;

    public CommunityMutation(CommunityService communityService) {
        this.communityService = communityService;
    }

    public Community createCommunity(CreateCommunityInput createCommunityInput) throws QiuSuoException {
        return this.communityService.createCommunity(
                createCommunityInput.getUserId(),
                createCommunityInput.getTitle(),
                createCommunityInput.getDescription(),
                createCommunityInput.getAvatarUrl(), createCommunityInput.getTags());
    }
}
