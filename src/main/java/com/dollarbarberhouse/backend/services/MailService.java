package com.dollarbarberhouse.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class MailService {
    private Logger LOG = LoggerFactory.getLogger(MailService.class);
    private final SpringTemplateEngine templateEngine;
    @Autowired
    private JavaMailSender emailSender;

    public MailService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void sendTestMail(String to, String subject, String text) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            Locale locale = Locale.forLanguageTag(Locale.ENGLISH.toString());
            Context context = new Context(locale);
            context.setVariable("email", to);
            String content = templateEngine.process("test", context);
            message.setFrom("sales.dollarbarberhouse@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content, true);
            emailSender.send(mimeMessage);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

    }
}
