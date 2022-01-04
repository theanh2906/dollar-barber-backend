package com.dollarbarberhouse.backend.services;

import com.dollarbarberhouse.backend.models.Accounts;
import com.dollarbarberhouse.backend.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Flux<Accounts> findAll() {
        return accountRepository.findAll();
    }

    public Flux<Accounts> addAccount(Accounts account) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setPassword(encoder.encode(account.getPassword()));
        return accountRepository.save(account).flatMapMany(acc -> accountRepository.findAll());
    }
}
