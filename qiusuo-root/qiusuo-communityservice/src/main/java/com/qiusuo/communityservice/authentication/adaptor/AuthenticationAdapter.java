package com.qiusuo.communityservice.authentication.adaptor;


import com.qiusuo.communityservice.authentication.config.CustomAuthenticationToken;
import com.qiusuo.communityservice.authentication.strategy.CellphoneAuthenticationStrategy;
import com.qiusuo.communityservice.authentication.strategy.GithubAuthenticationStrategy;
import com.qiusuo.communityservice.authentication.strategy.WeChatAuthenticationStrategy;
import com.qiusuo.communityservice.domain.model.UserType;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class AuthenticationAdapter {
    private GithubAuthenticationStrategy githubAuthenticationStrategy;
    private WeChatAuthenticationStrategy weChatAuthenticationStrategy;
    private CellphoneAuthenticationStrategy cellphoneAuthenticationStrategy;


    public Authentication authenticate(CustomAuthenticationToken authentication) {
        if (authentication.getAuthenticationType() == UserType.GITHUB) {
            return githubAuthenticationStrategy.authenticate(authentication);
        }
        return null;
    }
}
