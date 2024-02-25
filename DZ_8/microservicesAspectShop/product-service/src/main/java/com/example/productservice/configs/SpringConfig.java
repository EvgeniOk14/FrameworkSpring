package com.example.productservice.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
public class SpringConfig
{
    /** это класс Spring, который упрощает отправку HTTP-запросов к удаленным серверам и обработку ответов **/
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
        //  restTemplate это: экземпляр класса RestTemplate,
        // предоставляемый Spring Framework для отправки HTTP-запросов к удаленным серверам и обработки ответов.
    }
}