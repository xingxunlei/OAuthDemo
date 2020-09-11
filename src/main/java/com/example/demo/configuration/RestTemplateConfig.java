package com.example.demo.configuration;

import com.example.demo.client.SimonRestTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

/**
 * RestTemplate 的一点配置
 *
 * @author SimonX
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    SimonRestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setReadTimeout(3000);

        return new SimonRestTemplate(requestFactory);
    }

}
