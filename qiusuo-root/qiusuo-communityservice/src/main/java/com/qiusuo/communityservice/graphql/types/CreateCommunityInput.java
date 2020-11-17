package com.qiusuo.communityservice.graphql.types;


import lombok.Data;

import java.util.List;


/**
 * Data annotation is a shortcut for
 * implicit @Getter, @Setter, @ToString, @Equals And HashCode
 * and @RequiredArgsConstructor
 */
@Data
public class CreateCommunityInput {
    private String userId;
    private String title;
    private String description;
    private String avatarUrl;
    private List<String> tags;
}
