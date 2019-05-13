package com.dfjinxin.gate;

import com.dfjinxin.auth.client.EnableDfjinxinAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDfjinxinAuthClient
@EnableFeignClients({"com.dfjinxin.auth.client.feign","com.dfjinxin.gate.feign"})
public class DfjinxinGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfjinxinGateApplication.class, args);
    }

}
