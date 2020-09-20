package com.qiusuo.communityservice.graphql.mutation;


import lombok.Data;


/**
 * Data annotation is a shortcut for
 * implicit @Getter, @Setter, @ToString, @EqualsAndHashCode
 * and @RequiredArgsConstructor
 *
 */
@Data
public class CreateCommunityInput {
    private String ownerId;
    private String title;
    private String description;
    private String avatarUrl;
}
