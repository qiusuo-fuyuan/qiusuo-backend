package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}

