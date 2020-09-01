package com.qiusuo.community.domain.repository;

import com.qiusuo.community.domain.model.User;

public interface UserRepositoryCustom {
    User getUserByName(String name);
}
