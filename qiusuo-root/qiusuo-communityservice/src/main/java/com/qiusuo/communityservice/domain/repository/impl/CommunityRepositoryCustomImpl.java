package com.qiusuo.communityservice.domain.repository.impl;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.repository.CommunityRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommunityRepositoryCustomImpl implements CommunityRepositoryCustom {
    private JPAQueryFactory jpaQueryFactory;

    public CommunityRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Community> findCommunityByTest() {
        return Optional.empty();
    }
}
