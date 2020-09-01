package com.qiusuo.community.util.jwt;

import com.qiusuo.community.domain.model.UserType;
import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest {
    private String username;
    private String password;
    private UserType usertype;

    //userId is from Github/Wechat User
    private String userId;
    private String phoneNumber;

    //phone number verification code if login via phone.
    private String verificationCode;

    private String avatarUrl;
}
