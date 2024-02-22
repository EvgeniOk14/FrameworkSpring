package com.example.web_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.web_shop.models")
@EnableJpaRepositories("com.example.web_shop.repository")
public class WebShopApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(WebShopApplication.class, args);
    }

}
