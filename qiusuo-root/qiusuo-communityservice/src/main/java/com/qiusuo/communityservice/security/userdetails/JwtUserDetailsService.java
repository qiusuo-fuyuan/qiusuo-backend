package com.qiusuo.communityservice.security.userdetails;


import com.qiusuo.communityservice.domain.model.QUser;
import com.qiusuo.communityservice.domain.model.Role;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.security.userdetails.QiuSuoUserDetails;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * Note: using transactional on the class level will make Spring to manage the transaction session.
 * The session will be closed after the method call.
 */
@Transactional
@Service
public class JwtUserDetailsService  implements UserDetailsService{
    private JPAQueryFactory jpaQueryFactory;

    public JwtUserDetailsService(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public QiuSuoUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        QUser userToQuery = QUser.user;
        User userResult = jpaQueryFactory.selectFrom(userToQuery).where(userToQuery.userId.eq(userId)).fetchOne();

        QiuSuoUserBuilder userBuilder;
        if (userResult != null) {
            userBuilder = QiuSuoUser.withUserId(userId);
            userBuilder.parent().password(userResult.getEncryptedPassword());
            String roleName = userResult.getRoles().stream().map(Role::getName).collect(Collectors.joining(","));
            userBuilder.parent().roles(roleName).username(userResult.getName()).password(userResult.getEncryptedPassword());
        } else {
            throw new UsernameNotFoundException(String.format("User with user id not found %s", userId));
        }
        return userBuilder.build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
