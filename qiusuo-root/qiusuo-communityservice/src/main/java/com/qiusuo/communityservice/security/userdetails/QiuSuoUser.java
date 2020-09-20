package com.qiusuo.communityservice.security.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class QiuSuoUser extends User implements QiuSuoUserDetails {
    private String userId;

    public QiuSuoUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static QiuSuoUserBuilder withUserId(String userId) {
        QiuSuoUserBuilder userBuilder = QiuSuoUserBuilder.qiusuoUserBuilder();
        return userBuilder;
    }
}
