package com.dollarbarberhouse.backend.configurations;

import com.dollarbarberhouse.backend.models.Accounts;
import com.dollarbarberhouse.backend.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ScheduledTask {
    @Autowired
    private AccountService accountService;
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//    @Scheduled(cron = "*/10 * * * * *")
    public void addAccount() {
        accountService.addAccount(new Accounts(null, "Test_username", "Test_password")).subscribe(accounts -> {
            System.out.println("Success adding account!");
        }, throwable -> {
            System.out.println(throwable.getMessage());
        });
    }
}
