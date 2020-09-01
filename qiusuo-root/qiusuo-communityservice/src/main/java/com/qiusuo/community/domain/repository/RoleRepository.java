package com.qiusuo.community.domain.repository;

import com.qiusuo.community.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
