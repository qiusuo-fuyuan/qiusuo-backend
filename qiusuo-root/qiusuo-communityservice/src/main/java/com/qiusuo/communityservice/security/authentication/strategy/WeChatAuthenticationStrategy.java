package com.qiusuo.communityservice.security.authentication.strategy;

import com.qiusuo.communityservice.security.authentication.QiuSuoAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;


@Configuration
public class WeChatAuthenticationStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatAuthenticationStrategy.class);

    /*
     */
    public Authentication authenticate(QiuSuoAuthenticationToken authentication) {
        LOGGER.debug("authenticate via wechat account");
        String accessToken = authentication.getUsername();
        //call github
        return authentication;
    }
}
