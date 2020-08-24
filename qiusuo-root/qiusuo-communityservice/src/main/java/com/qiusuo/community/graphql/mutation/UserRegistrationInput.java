package com.qiusuo.community.graphql.mutation;

import lombok.Data;

@Data
public class UserRegistrationInput {
    private String name;
    private String password;
}
