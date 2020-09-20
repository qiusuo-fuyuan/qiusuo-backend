package com.qiusuo.communityservice.graphql.mutation;

import lombok.Data;

@Data
public class CreateChannelInput {
    private Long communityId;
    private String name;
}
