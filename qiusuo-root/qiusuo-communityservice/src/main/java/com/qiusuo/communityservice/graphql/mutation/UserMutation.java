package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.domain.model.User;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    public User registerUser(UserRegistrationInput userRegistrationInput) {
        return null;
    }
}
