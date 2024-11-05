package com.fdi17.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: springboot
 * @description: main class
 * @author: zhou
 * @create: 2024-04-11 10:15
 **/
@SpringBootApplication
@EnableFeignClients
public class Fdi17CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(Fdi17CommonApplication.class, args);
    }
}
