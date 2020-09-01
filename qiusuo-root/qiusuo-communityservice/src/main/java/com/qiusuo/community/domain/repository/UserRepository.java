package com.qiusuo.community.domain.repository;

import com.qiusuo.community.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>,UserRepositoryCustom  {
}
