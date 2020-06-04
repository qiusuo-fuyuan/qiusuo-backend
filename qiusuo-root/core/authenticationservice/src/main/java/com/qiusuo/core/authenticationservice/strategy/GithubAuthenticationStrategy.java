package com.qiusuo.core.authenticationservice.strategy;

import com.qiusuo.core.authenticationservice.config.CustomAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

/*
What we will do is to use HttpClient to send one query request to github using the accessToken. If it returns
successful, then authentication sucess
 */
@Configuration
public class GithubAuthenticationStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubAuthenticationStrategy.class);
    /*
     */
    public Authentication authenticate(CustomAuthenticationToken authentication) {
        LOGGER.debug("authenticate via github account");
        String accessToken = authentication.getAccessToken();
        //call github
        return authentication;
    }
}
