package com.qiusuo.communityservice.graphql.mutation;

import com.qiusuo.communityservice.security.authentication.QiuSuoAuthenticationToken;
import com.qiusuo.communityservice.security.userdetails.JwtUserDetailsService;
import com.qiusuo.communityservice.security.userdetails.QiuSuoUserDetails;
import com.qiusuo.communityservice.util.jwt.JwtRequest;
import com.qiusuo.communityservice.util.jwt.JwtResponse;
import com.qiusuo.communityservice.util.jwt.JwtTokenUtil;
import com.qiusuo.communityservice.domain.model.UserType;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
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

    //unique id is stored in userId field
    public JwtResponse createJwtToken(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getUserId(),
                authenticationRequest.getPassword(), authenticationRequest.getUsertype(),authenticationRequest.getAvatarUrl());
        final QiuSuoUserDetails userDetails = userDetailsService
                .loadUserByUserId(authenticationRequest.getUserId());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }


    private void authenticate(String username, String userId, String password, UserType usertype,String avatarUrl) throws Exception {
        try {
            authenticationManager.authenticate(new QiuSuoAuthenticationToken(username, userId, password, usertype,avatarUrl));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}