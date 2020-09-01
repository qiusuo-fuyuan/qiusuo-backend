package com.qiusuo.community.graphql.mutation;

import com.qiusuo.community.authentication.config.CustomAuthenticationToken;
import com.qiusuo.community.authentication.config.JwtUserDetailsService;
import com.qiusuo.community.util.jwt.JwtRequest;
import com.qiusuo.community.util.jwt.JwtResponse;
import com.qiusuo.community.util.jwt.JwtTokenUtil;
import com.qiusuo.community.domain.model.UserType;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthMutation implements GraphQLMutationResolver {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    public JwtResponse createToken(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword(), authenticationRequest.getAccessToken(), authenticationRequest.getUsertype());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }


    private void authenticate(String username, String password, String accessToken, UserType usertype) throws Exception {
        try {
            authenticationManager.authenticate(new CustomAuthenticationToken(username, password, accessToken, usertype));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
