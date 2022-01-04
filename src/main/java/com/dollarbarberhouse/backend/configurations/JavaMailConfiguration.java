package com.dollarbarberhouse.backend.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfiguration {
    @Value("${spring.mail.username}")
    private String EMAIL_ADDRESS;
    @Value("${spring.mail.password}")
    private String PASSWORD;
    @Value("${spring.mail.host}")
    private String HOST;
    @Value("${spring.mail.port}")
    private Integer PORT;
    private static final String TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    private static final String SMTP = "smtp";
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST);
        mailSender.setPort(PORT);
        mailSender.setUsername(EMAIL_ADDRESS);
        mailSender.setPassword(PASSWORD);
        Properties props = mailSender.getJavaMailProperties();
        props.put(TRANSPORT_PROTOCOL, SMTP);
        props.put(SMTP_AUTH, Boolean.TRUE);
        props.put(SMTP_STARTTLS_ENABLE, Boolean.TRUE);
        return mailSender;
    }
}
