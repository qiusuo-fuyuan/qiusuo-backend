package com.qiusuo.communityservice.domain.repository.impl;

import com.qiusuo.communityservice.domain.model.QUser;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;

    public UserRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public User findUserByUserId(String userId) {
        QUser user = QUser.user;
        User existingUser = jpaQueryFactory.selectFrom(user)
                .where(user.userId.eq(userId))
                .fetchOne();
        if(existingUser == null) {
            return null;
            //throw new UsernameNotFoundException(String.format("user with userId=%s not found", userId));
        }
        return existingUser;
    }
}
