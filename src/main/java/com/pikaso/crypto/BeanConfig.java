package com.pikaso.crypto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
