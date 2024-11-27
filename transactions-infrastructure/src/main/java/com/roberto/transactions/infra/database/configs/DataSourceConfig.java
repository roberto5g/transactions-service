package com.roberto.transactions.infra.database.configs;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Slf4j
@Configuration
@Setter(AccessLevel.PROTECTED)
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource(){

        final var jdbc = new DriverManagerDataSource();
        jdbc.setDriverClassName(driverClassName);
        jdbc.setUrl(url);
        jdbc.setUsername(username);
        jdbc.setPassword(password);
        return jdbc;
    }
}
