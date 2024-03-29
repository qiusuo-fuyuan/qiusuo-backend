package com.qiusuo.communityservice.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

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

    @ManyToMany
    @JoinTable(
            name = "communities_users",
            joinColumns = @JoinColumn(
                    name = "community_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id")
    )
    private Collection<User> subscribedUsers;

    /*TODO: Channel should belong to communities. Currently it's
    separate table.
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "community_id")
    private Collection<Channel> channels;
}
