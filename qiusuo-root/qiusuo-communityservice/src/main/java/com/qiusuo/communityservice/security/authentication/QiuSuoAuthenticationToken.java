package com.qiusuo.communityservice.security.authentication;

import com.qiusuo.communityservice.domain.model.UserType;
import com.qiusuo.communityservice.security.userdetails.QiuSuoUserDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class QiuSuoAuthenticationToken extends AbstractAuthenticationToken {
    @Getter
    @Setter
    private UserType authenticationType;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String userId;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String avatarUrl;


    public QiuSuoAuthenticationToken(QiuSuoUserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        setUsername(userDetails.getUsername());
        setPassword(userDetails.getPassword());
        setUserId(userDetails.getUserId());
    }

    public QiuSuoAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public QiuSuoAuthenticationToken(String username, String userId, String password,
                                     UserType authenticationType, String avatarUrl) {
        super(null);
        this.username = username;
        this.authenticationType = authenticationType;
        this.password = password;
        this.userId = userId;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
