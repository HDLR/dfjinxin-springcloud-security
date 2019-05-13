/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin;

import com.dfjinxin.auth.client.EnableDfjinxinAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDfjinxinAuthClient
@EnableDiscoveryClient
@EnableFeignClients({"com.dfjinxin.auth.client.feign"})
public class DfjinxinFastApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfjinxinFastApplication.class, args);
	}

}

