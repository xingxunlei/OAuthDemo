package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Oauth demo
 *
 * @author SimonX
 */
@Slf4j
@SpringBootApplication
public class OauthDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthDemoApplication.class, args);
        log.info("启动成功！！！");
    }

}
