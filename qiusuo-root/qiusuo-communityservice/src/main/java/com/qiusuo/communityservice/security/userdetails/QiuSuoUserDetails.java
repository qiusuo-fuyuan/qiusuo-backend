package com.qiusuo.communityservice.security.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public interface QiuSuoUserDetails extends UserDetails {
    String getUserId();
}
