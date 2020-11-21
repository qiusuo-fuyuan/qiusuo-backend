package com.qiusuo.communityservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class QiuSuoCommunityService {
    public static void main(String[] args)  {
        SpringApplication.run(QiuSuoCommunityService.class, args);
    }
}
