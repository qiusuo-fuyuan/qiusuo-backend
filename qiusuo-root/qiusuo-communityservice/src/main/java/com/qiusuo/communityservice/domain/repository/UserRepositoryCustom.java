package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.User;

public interface UserRepositoryCustom {
    /**
     * Find the user by userId. userId is not the primary key of the user. Some accounts like
     * Github or Wechat, they have userId field. We directly persisted this field in our DB
     *
     * @param userId
     * @return
     */
    User findUserByUserId(String userId);
}
