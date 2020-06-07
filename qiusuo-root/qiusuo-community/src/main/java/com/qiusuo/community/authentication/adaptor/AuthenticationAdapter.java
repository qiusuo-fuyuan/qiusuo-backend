package com.qiusuo.community.authentication.adaptor;

import com.qiusuo.core.authenticationservice.config.CustomAuthenticationToken;
import com.qiusuo.core.authenticationservice.model.UserType;
import com.qiusuo.core.authenticationservice.strategy.CellphoneAuthenticationStrategy;
import com.qiusuo.core.authenticationservice.strategy.GithubAuthenticationStrategy;
import com.qiusuo.core.authenticationservice.strategy.WeChatAuthenticationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAdapter {
    @Autowired
    GithubAuthenticationStrategy githubAuthenticationStrategy;
    @Autowired
    WeChatAuthenticationStrategy weChatAuthenticationStrategy;
    @Autowired
    CellphoneAuthenticationStrategy cellphoneAuthenticationStrategy;

    public Authentication authenticate(CustomAuthenticationToken authentication) {
        if(authentication.getAuthenticationType() == UserType.GITHUB) {
            return githubAuthenticationStrategy.authenticate(authentication);
        }
        return null;
    }

}
