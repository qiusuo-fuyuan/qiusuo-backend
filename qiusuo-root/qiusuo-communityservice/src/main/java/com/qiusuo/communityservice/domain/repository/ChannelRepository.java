package com.qiusuo.communityservice.domain.repository;

import com.qiusuo.communityservice.domain.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
