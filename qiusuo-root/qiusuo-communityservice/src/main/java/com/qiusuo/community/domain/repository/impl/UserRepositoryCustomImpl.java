package com.qiusuo.community.domain.repository.impl;

import com.qiusuo.community.domain.model.QUser;
import com.qiusuo.community.domain.model.User;
import com.qiusuo.community.domain.repository.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;

    public UserRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public User getUserByName(String name) {
        QUser user = QUser.user;
        User existingUser = jpaQueryFactory.selectFrom(user)
                .where(user.name.eq(name))
                .fetchOne();
        if(existingUser == null) {
            throw new UsernameNotFoundException("username with name not found");
        }
        return existingUser;
    }
}
