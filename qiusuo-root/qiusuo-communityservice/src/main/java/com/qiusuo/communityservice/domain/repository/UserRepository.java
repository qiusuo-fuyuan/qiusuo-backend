package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>,UserRepositoryCustom  {
}
