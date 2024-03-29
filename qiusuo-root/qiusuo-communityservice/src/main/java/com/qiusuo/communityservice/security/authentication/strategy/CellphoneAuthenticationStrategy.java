package com.qiusuo.communityservice.security.authentication.strategy;

import com.qiusuo.communityservice.security.authentication.QiuSuoAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

@Configuration
public class CellphoneAuthenticationStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(CellphoneAuthenticationStrategy.class);

    /*
     */
    public Authentication authenticate(QiuSuoAuthenticationToken authentication) {
        LOGGER.debug("authenticate via cellphone account");
        String accessToken = authentication.getUsername();
        //call cellphone verification code.
        return authentication;
    }
}
