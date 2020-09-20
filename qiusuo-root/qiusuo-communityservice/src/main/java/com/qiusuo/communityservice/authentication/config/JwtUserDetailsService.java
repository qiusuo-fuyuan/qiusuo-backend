package com.qiusuo.communityservice.authentication.config;


import com.qiusuo.communityservice.domain.model.QUser;
import com.qiusuo.communityservice.domain.model.Role;
import com.qiusuo.communityservice.domain.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Transactional
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private JPAQueryFactory jpaQueryFactory;

    public JwtUserDetailsService(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QUser userToQuery = QUser.user;
        User userResult = jpaQueryFactory.selectFrom(userToQuery).where(userToQuery.userId.eq(username)).fetchOne();

        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (userResult != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(userResult.getEncryptedPassword());
            String roleName = userResult.getRoles().stream().map(Role::getName).collect(Collectors.joining(","));
            builder.roles(roleName).username(userResult.getName()).password(userResult.getEncryptedPassword());
        } else {
            throw new UsernameNotFoundException(String.format("User not found %s",username));
        }
        return builder.build();
    }
}
