package com.qiusuo.communityservice.security.authentication;

import com.qiusuo.communityservice.security.authentication.AuthenticationAdapter;
import com.qiusuo.communityservice.security.authentication.QiuSuoAuthenticationToken;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
public class CustomAuthenticationProvider implements AuthenticationProvider {
    AuthenticationAdapter authenticationAdapter;

    CustomAuthenticationProvider(AuthenticationAdapter authenticationAdapter) {
        this.authenticationAdapter = authenticationAdapter;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        return authenticationAdapter.authenticate((QiuSuoAuthenticationToken) authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(QiuSuoAuthenticationToken.class);
    }
}