package com.qiusuo.communityservice.domain.service;

import com.qiusuo.communityservice.domain.model.Community;
import com.qiusuo.communityservice.domain.model.User;
import com.qiusuo.communityservice.domain.repository.UserRepository;
import com.qiusuo.communityservice.security.authentication.QiuSuoAuthenticationToken;
import org.hibernate.Hibernate;
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

    public Collection<Community> getCommunitiesForCurrentUser() {
        User user = getCurrentUser();
        /*here, it actually returns the proxy. Only when we try to fetch
        one of those values, Hibernate.initialize will initialize lazy objects
        */
        user.getSubscribedCommunities().forEach(community -> {
            Hibernate.initialize(community.getChannels());
        });
        return user.getSubscribedCommunities();
    }

    public User createUser(String name) {
        User newUser = new User();
        newUser.setName(name);
        userRepository.save(newUser);
        return newUser;
    }

    public User findUserById(String id) {
        return userRepository.getOne(Long.parseLong(id));
    }

    /**
     * Get the current username from spring security context holder
     * There is always one current user. Either anonymous user or
     * logged in user
     * <p>
     * TODO This method will not work, when we use Reactive Programming, we have
     * since the authentication object is set in the ThreadLocal object. Will find out later
     *
     * @return
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof QiuSuoAuthenticationToken) {
            QiuSuoAuthenticationToken qiuSuoAuthenticationToken = (QiuSuoAuthenticationToken) authentication;
            return userRepository.findUserByUserId(qiuSuoAuthenticationToken.getUserId());
        } else {
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