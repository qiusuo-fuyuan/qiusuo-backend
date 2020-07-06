package com.qiusuo.community.authentication.strategy;


import com.qiusuo.community.authentication.config.CustomAuthenticationToken;
import com.qiusuo.community.authentication.repository.UserRepository;
import com.qiusuo.community.domain.model.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;

/*
What we will do is to use HttpClient to send one query request to github using the accessToken. If it returns
successful, then authentication sucess
 */
@Configuration
public class GithubAuthenticationStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubAuthenticationStrategy.class);

    JPAQueryFactory jpaQueryFactory;
    UserRepository userRepository;

    public GithubAuthenticationStrategy(JPAQueryFactory jpaQueryFactory, UserRepository userRepository) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.userRepository = userRepository;
    }

    /*
     */
    public Authentication authenticate(CustomAuthenticationToken authentication) {
        LOGGER.debug("authenticate via github account");
        String username = authentication.getUsername();
        QUser user = QUser.user;
        User existingUser = jpaQueryFactory.selectFrom(user)
                .where(user.name.eq(username))
                .fetchOne();

        if (existingUser == null) {
            User newUser = new User();
            newUser.setEnabled(true);
            newUser.setName(username);
            newUser.setUserType(UserType.GITHUB);
            newUser.setEncryptedPassword(authentication.getUserId());
            newUser.setRoles(getRoles("ROLE_USER"));
            userRepository.save(newUser);
        } else {
            userRepository.save(existingUser);
        }
        return authentication;
    }

    private ArrayList<Role> getRoles(String roleName) {
        QRole role = QRole.role;
        Role roleUser = jpaQueryFactory.selectFrom(role).where(role.name.eq(roleName)).fetchOne();
        ArrayList<Role> roles = new ArrayList<Role>() {
            {
                add(roleUser);
            }
        };
        return roles;
    }
}