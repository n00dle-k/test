package com.test.controllers;

import com.test.models.Account;
import com.test.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class RegisterController {
    private final AccountService accountService;

    @Autowired
    public RegisterController(
            AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("account", new Account());
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("account") Account account,
            BindingResult bindingResult) throws IOException {
        accountService.validateNewAccount(account, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        accountService.createNewUser(account);
        return "rerdirect:/house";
    }
}
