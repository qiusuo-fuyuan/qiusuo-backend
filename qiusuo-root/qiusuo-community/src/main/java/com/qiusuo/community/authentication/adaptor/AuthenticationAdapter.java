package com.qiusuo.community.authentication.adaptor;


import com.qiusuo.community.authentication.config.CustomAuthenticationToken;
import com.qiusuo.community.authentication.strategy.CellphoneAuthenticationStrategy;
import com.qiusuo.community.authentication.strategy.GithubAuthenticationStrategy;
import com.qiusuo.community.authentication.strategy.WeChatAuthenticationStrategy;
import com.qiusuo.community.domain.model.UserType;
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
