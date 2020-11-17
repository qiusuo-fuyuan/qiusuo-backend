package com.qiusuo.communityservice.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.Collection;

/**
 * Note: Lombok might change the hash() function. It might result in
 * comparison error. Just keep this in mind.
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_seq")
    private Long id;

    @Column(unique = true)
    private String userId;
    private String name;

    @Column(unique = true)
    private String phoneNumber;
    /**
     * the user password is encrypted when stored in database
     */
    private String encryptedPassword;

    private Boolean enabled;

    private UserType userType;

    private String avatarUrl;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;


    @ManyToMany(mappedBy = "subscribedUsers")
    private Collection<Community> subscribedCommunities;

    @OneToOne
    private Profile userProfile;

    @OneToOne
    private Community activeCommunity;

    @OneToOne
    private Channel activeChannel;
}
