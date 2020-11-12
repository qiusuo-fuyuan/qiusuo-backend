package com.qiusuo.communityservice.security.filter;

import com.qiusuo.communityservice.security.authentication.QiuSuoAuthenticationToken;
import com.qiusuo.communityservice.security.userdetails.JwtUserDetailsService;
import com.qiusuo.communityservice.security.userdetails.QiuSuoUserDetails;
import com.qiusuo.communityservice.util.jwt.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
        * TODO:
        * (1)handle when token has expired
        * (2)handle when frontend send one invalid token
        */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService jwtUserDetailsService;

    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        /*
        what need to do is first check
         */
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("Authorization is "+requestTokenHeader);
        String userId = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                //What need to do here is to put the UserDetails information in the SecurityContextHolder.
                userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                LOGGER.error("JWT Token has expired");
            }
        } else {
            LOGGER.warn("JWT Token does not begin with Bearer String");
        }
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            QiuSuoUserDetails userDetails = this.jwtUserDetailsService.loadUserByUserId(userId);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                QiuSuoAuthenticationToken qiuSuoAuthenticationToken = new QiuSuoAuthenticationToken(
                        userDetails, userDetails.getAuthorities());
                qiuSuoAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                qiuSuoAuthenticationToken.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(qiuSuoAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}