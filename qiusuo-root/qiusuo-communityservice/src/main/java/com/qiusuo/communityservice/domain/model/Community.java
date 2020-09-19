package com.qiusuo.communityservice.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @Data is equal to @Setter @Getter @hashCode @equal.
 * The hashCode should be careful since its used in @equal for equality
 * check. This might cause bugs
 */
@Data
@Entity
public class Community {
    @Id
    @GeneratedValue(generator = "community_id_generator")
    @SequenceGenerator(name = "community_id_generator", sequenceName = "community_seq")
    private Long id;

    private String title;
    private String description;
    private String avatarUrl;

    //comma separated tags
    private String commaSeparatedTags;

    @ManyToMany(mappedBy = "subscribedCommunities")
    private Collection<User> subscribedUsers;

    @OneToMany(mappedBy = "community",cascade = CascadeType.PERSIST)
    private Collection<Channel> channels;
}
