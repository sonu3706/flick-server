package com.flick.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FlickServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlickServerApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
      return new RestTemplate();
    }

}
