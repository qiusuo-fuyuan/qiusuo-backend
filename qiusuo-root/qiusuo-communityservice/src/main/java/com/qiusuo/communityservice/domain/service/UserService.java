package com.qiusuo.communityservice.domain.service;

import com.qiusuo.communityservice.authentication.config.CustomAuthenticationToken;
import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Collection<Community> getCommunitiesForUserId(String userId) {
        User user = userRepository.findUserByUserId(userId);
        /*here, it actually returns the proxy. Only when we try to fetch
        one of those values, then Hibernate will use the current session
        to fetch the values.
        */
        user.getSubscribedCommunities().size();//TODO change later.
        return user.getSubscribedCommunities();
    }

    public User createUser(String name) {
        User newUser = new User();
        newUser.setName(name);
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * Get the current username from spring security context holder
     * There is always one current user. Either anonymous user or
     * logged in user
     * @return
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authentication;
            return userRepository.findUserByUserId(customAuthenticationToken.getUsername());
        }
        else {
            /*
            TODO: user information for anonymous user
             */
            AnonymousAuthenticationToken authenticationToken = (AnonymousAuthenticationToken) authentication;
            User anonymousUser = new User();
            anonymousUser.setName("anonymous");
            return anonymousUser;
        }
    }
}