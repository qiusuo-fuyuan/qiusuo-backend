package com.qiusuo.communityservice.security.authentication;


import com.qiusuo.communityservice.security.authentication.strategy.CellphoneAuthenticationStrategy;
import com.qiusuo.communityservice.security.authentication.strategy.GithubAuthenticationStrategy;
import com.qiusuo.communityservice.security.authentication.strategy.WeChatAuthenticationStrategy;
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


    public Authentication authenticate(QiuSuoAuthenticationToken authentication) {
        if (authentication.getAuthenticationType() == UserType.GITHUB) {
            return githubAuthenticationStrategy.authenticate(authentication);
        }
        return null;
    }
}
