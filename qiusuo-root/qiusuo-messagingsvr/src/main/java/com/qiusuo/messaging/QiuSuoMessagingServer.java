package com.qiusuo.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class QiuSuoMessagingServer {
    public static void main(String[] args)  {
        SpringApplication.run(QiuSuoMessagingServer.class, args);
    }
}
