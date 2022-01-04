package com.dollarbarberhouse.backend.rest;

import com.dollarbarberhouse.backend.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mails")
public class MailController {
    @Autowired
    private MailService mailService;
    @GetMapping("/sendMail")
    @ResponseBody
    public String helloWorld() {
        mailService.sendTestMail("heoiunieng@gmail.com", "Test mail", "This is test mail");
        return "Hello World";
    }
}
