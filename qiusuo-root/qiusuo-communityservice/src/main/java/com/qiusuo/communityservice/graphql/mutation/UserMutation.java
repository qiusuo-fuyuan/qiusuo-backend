package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.User;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    public User registerUser(com.qiusuo.communityservice.graphql.mutation.UserRegistrationInput userRegistrationInput) {
        return null;
    }
}
