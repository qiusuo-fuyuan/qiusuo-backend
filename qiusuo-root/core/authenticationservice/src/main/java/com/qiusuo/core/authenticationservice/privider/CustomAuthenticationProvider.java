package com.qiusuo.core.authenticationservice.privider;

import com.qiusuo.core.authenticationservice.adaptor.AuthenticationAdapter;
import com.qiusuo.core.authenticationservice.config.CustomAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
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
        return authenticationAdapter.authenticate((CustomAuthenticationToken) authentication);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }
}
