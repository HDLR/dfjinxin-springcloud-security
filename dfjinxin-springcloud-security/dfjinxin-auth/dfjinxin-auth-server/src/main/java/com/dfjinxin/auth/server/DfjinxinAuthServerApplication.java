package com.dfjinxin.auth.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.dfjinxin.auth.server.mapper")
@EnableAutoConfiguration
public class DfjinxinAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfjinxinAuthServerApplication.class, args);
    }

}
