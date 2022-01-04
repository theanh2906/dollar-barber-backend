package com.dollarbarberhouse.backend.rest;

import com.dollarbarberhouse.backend.http.AccountsResource;
import com.dollarbarberhouse.backend.models.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private AccountsResource resource;
    @GetMapping("")
    public List<Accounts> hello() {
        return resource.getAllAccounts();
    }
}
