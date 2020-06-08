package com.qiusuo.community.authentication.repository;

import com.qiusuo.community.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
