package com.qiusuo.eurekasvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QiuSuoEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(QiuSuoEurekaServer.class, args);
    }
}