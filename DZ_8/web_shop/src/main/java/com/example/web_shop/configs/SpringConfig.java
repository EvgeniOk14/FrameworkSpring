package com.example.web_shop.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class SpringConfig
{
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("oew");
        return dataSource;
    }

    /**
     * создаёт соединение с БД
     **/
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    /** это класс Spring, который упрощает отправку HTTP-запросов к удаленным серверам и обработку ответов **/
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
    //  restTemplate это: экземпляр класса RestTemplate,
    // предоставляемый Spring Framework для отправки HTTP-запросов к удаленным серверам и обработки ответов.