package com.qiusuo.configurationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class QiuSuoConfigurationService {
    public static void main(String[] args) {
        SpringApplication.run(QiuSuoConfigurationService.class, args);
    }
}

