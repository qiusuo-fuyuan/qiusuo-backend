package com.qiusuo.community.graphql.mutation;

import com.qiusuo.community.domain.model.User;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    public User registerUser(UserRegistrationInput userRegistrationInput) {
        return null;
    }
}
