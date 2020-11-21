package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {

    /**
     * Find the community by communityId.
     *
     * @param communityId the PK of the community
     * @return
     */
    @Override
    Optional<Community> findById(Long communityId);
}
