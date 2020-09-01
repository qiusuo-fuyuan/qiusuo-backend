package com.qiusuo.community.graphql.mutation;

import com.qiusuo.community.authentication.config.CustomAuthenticationToken;
import com.qiusuo.community.authentication.config.JwtUserDetailsService;
import com.qiusuo.community.util.jwt.JwtRequest;
import com.qiusuo.community.util.jwt.JwtResponse;
import com.qiusuo.community.util.jwt.JwtTokenUtil;
import com.qiusuo.community.domain.model.UserType;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Secured("ROLE_ANONYMOUS")
@Component
public class AuthMutation implements GraphQLMutationResolver {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;

    public AuthMutation(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public JwtResponse createJwtToken(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getUserId(),
                authenticationRequest.getPassword(), authenticationRequest.getUsertype(),authenticationRequest.getAvatarUrl());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }


    private void authenticate(String username, String userId, String password, UserType usertype,String avatarUrl) throws Exception {
        try {
            authenticationManager.authenticate(new CustomAuthenticationToken(username, userId, password, usertype,avatarUrl));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}