package com.dollarbarberhouse.backend.web;

import com.dollarbarberhouse.backend.http.AccountsResource;
import com.dollarbarberhouse.backend.models.Accounts;
import com.dollarbarberhouse.backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller()
@RequestMapping("/tools")
public class ToolWebController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountsResource accountsResource;
    @GetMapping("/fromExcelToJSON")
    public String fromExcelToJSON(Model model) {
        List<Accounts> accounts = accountsResource.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "tools/fromExcelToJSON";
    }
}
