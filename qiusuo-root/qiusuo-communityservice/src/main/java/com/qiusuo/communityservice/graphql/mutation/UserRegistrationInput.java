package com.qiusuo.communityservice.graphql.mutation;

import lombok.Data;

@Data
public class UserRegistrationInput {
    private String name;
    private String password;
}
