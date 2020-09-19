package com.qiusuo.communityservice.util.jwt;

import com.qiusuo.communityservice.domain.model.UserType;
import lombok.Data;

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
