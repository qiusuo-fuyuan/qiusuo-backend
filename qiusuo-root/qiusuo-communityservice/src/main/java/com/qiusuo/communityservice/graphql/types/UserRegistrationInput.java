package com.qiusuo.communityservice.graphql.types;

import lombok.Data;

@Data
public class UserRegistrationInput {
    private String name;
    private String password;
}
