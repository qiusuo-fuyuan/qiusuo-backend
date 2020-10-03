package com.qiusuo.configsvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class QiuSuoConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(QiuSuoConfigServer.class, args);
    }
}

