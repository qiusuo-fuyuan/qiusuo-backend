package com.qiusuo.communityservice.security.authentication.strategy;


import com.qiusuo.communityservice.security.authentication.QiuSuoAuthenticationToken;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import com.qiusuo.communityservice.domain.model.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/*
What we will do is to use HttpClient to send one query request to github using the accessToken. If it returns
successful, then authentication sucess
 */
@Configuration
public class GithubAuthenticationStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubAuthenticationStrategy.class);
    private static final String USERID_SUFFIX = "_github";
    UserRepository userRepository;
    JPAQueryFactory jpaQueryFactory;

    public GithubAuthenticationStrategy(JPAQueryFactory jpaQueryFactory, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }
    /*
     */
    public Authentication authenticate(QiuSuoAuthenticationToken authentication) {
        LOGGER.debug("user authentication github with username {} and userId {}",
                authentication.getUsername(), authentication.getUserId());

        /**
         * TODO:
         * here, name and id is interleaved. We will change that to be consistent
         */
        String userId = authentication.getUserId();
        //for third party user, we put the platform as suffixes for the user id

        User existingUser;
        try {
            existingUser = userRepository.findUserByUserId(userId);
        } catch (UsernameNotFoundException e) {
            User newUser = new User();
            newUser.setEnabled(true);
            newUser.setName(authentication.getUsername());
            newUser.setUserType(UserType.GITHUB);
            newUser.setUserId(authentication.getUserId());
            newUser.setAvatarUrl(authentication.getAvatarUrl());
            /*We set the password because when building user in JwtUserService
            Password is one mandatory field
             */
            newUser.setEncryptedPassword(authentication.getUserId());
            newUser.setRoles(getRoles("USER"));
            userRepository.save(newUser);
            LOGGER.debug("user authentication: generating github user with name {} and userId {}",
                    authentication.getUsername(), authentication.getUserId());
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