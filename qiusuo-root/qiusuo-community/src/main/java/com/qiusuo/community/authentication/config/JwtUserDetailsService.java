package com.qiusuo.community.authentication.config;


import com.qiusuo.community.domain.model.Role;
import com.qiusuo.community.domain.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private JPAQueryFactory jpaQueryFactory;

    public JwtUserDetailsService(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QUser userToQuery = QUser.user;
        User userResult = jpaQueryFactory.selectFrom(userToQuery).where(userToQuery.name.eq(username)).fetchOne();

        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (userResult != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(userResult.getEncryptedPassword());
            builder.roles(userResult.getRoles().stream().map(Role::getName).toString());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

}