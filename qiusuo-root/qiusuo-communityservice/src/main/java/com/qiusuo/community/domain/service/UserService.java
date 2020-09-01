package com.qiusuo.community.domain.service;

import com.qiusuo.community.authentication.config.CustomAuthenticationToken;
import com.qiusuo.community.domain.model.User;
import com.qiusuo.community.domain.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User addUser(String name) {
        User newUser = new User();
        newUser.setName(name);
        userRepository.save(newUser);
        return newUser;
    }

    /**
     * Get the current username from the
     *
     * @return
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authentication;
            return getUserByName(customAuthenticationToken.getUsername());
        }
        return null;
    }

    public User getUserByName(String name) {
        return userRepository.getUserByName(name);
    }
}