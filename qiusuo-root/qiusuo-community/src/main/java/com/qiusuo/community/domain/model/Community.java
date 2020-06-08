package com.qiusuo.community.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import java.util.Collection;

@Data
@Entity
public class Community {
    @Id
    @GeneratedValue(generator = "community_id_generator")
    @SequenceGenerator(name = "community_id_generator", sequenceName = "community_seq")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "subscribedCommunities")
    private Collection<User> subscribedUsers;
}
