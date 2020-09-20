package com.qiusuo.communityservice.security.userdetails;

import org.springframework.security.core.userdetails.User;

import static org.springframework.security.core.userdetails.User.builder;

/**
 * This code looks very complicated.
 * We only need Authorities, userId, username, password,
 * and maybe phone number
 */
public class QiuSuoUserBuilder {
    private User.UserBuilder parent;
    private  String userId;

    private QiuSuoUserBuilder() {
    }

    public static QiuSuoUserBuilder userBuilder() {
        QiuSuoUserBuilder userBuilder = new QiuSuoUserBuilder();
        userBuilder.parent = builder();
        return userBuilder;
    }

    public QiuSuoUserBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public User.UserBuilder parent() {
        return parent;
    }

    public QiuSuoUserDetails build() {
        User user = (User) this.parent().build();
        //we might do not need username and passworld
        QiuSuoUser qiusuoUser = new QiuSuoUser(user.getUsername(),user.getPassword(),user.getAuthorities());
        qiusuoUser.setUserId(userId);
        return qiusuoUser;
    }
}
