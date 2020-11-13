package com.qiusuo.communityservice.security.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationStrategy {
     Authentication authenticate(QiuSuoAuthenticationToken authentication);
}
