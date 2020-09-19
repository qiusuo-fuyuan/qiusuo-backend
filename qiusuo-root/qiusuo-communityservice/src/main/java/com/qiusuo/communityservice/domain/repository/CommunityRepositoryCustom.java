package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.Community;

import java.util.Optional;

public interface CommunityRepositoryCustom {
    Optional<Community> findCommunityByTest();
}
