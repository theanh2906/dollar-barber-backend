package com.dollarbarberhouse.backend.rest;

import com.dollarbarberhouse.backend.models.Accounts;
import com.dollarbarberhouse.backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public Flux<Accounts> getAllAccounts() {
        return accountService.findAll().doOnError(throwable -> {
            try {
                throw new Exception(throwable.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @PostMapping("")
    public Flux<Accounts> addAccount(@RequestBody Accounts account) {
        return accountService.addAccount(account);
    }
}
