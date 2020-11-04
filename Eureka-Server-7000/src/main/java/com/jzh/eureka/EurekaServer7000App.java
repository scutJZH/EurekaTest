package com.jzh.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7000App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7000App.class, args);
    }
}
