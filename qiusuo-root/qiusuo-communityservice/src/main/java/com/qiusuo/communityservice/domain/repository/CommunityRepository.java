package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    @Override
    Optional<Community> findById(Long aLong);
}
