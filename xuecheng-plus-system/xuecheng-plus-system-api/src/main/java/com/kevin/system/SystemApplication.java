package com.kevin.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 *     系统管理启动类
 * </p>
 *
 * @Description:
 */
@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class SystemApplication {
    public static void main(String[] args) {
        System.out.println("hello world");
        SpringApplication.run(SystemApplication.class,args);
    }
}