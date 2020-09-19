package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.User;

public interface UserRepositoryCustom {
    User getUserByName(String name);
}
