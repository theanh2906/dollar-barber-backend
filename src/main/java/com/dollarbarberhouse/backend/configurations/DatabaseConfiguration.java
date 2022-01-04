//package com.dollarbarberhouse.backend.configurations;
//
//import io.r2dbc.h2.H2ConnectionConfiguration;
//import io.r2dbc.h2.H2ConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//
//@Configuration
//@EnableR2dbcRepositories("com.dollarbarberhouse.backend.repositories")
//public class DatabaseConfiguration extends AbstractR2dbcConfiguration {
//
//    @Value("${spring.datasource.username}")
//    private String USERNAME;
//    @Value("${spring.datasource.password}")
//    private String PASSWORD;
//
//    @Override
//    public H2ConnectionFactory connectionFactory() {
//        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
//                .url("mem:main_db")
//                .username(USERNAME)
//                .password(PASSWORD)
//                .build());
//    }
//}
