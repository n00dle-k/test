package com.test.controllers;

import com.test.models.Account;
import com.test.services.AccountService;
import org.springframework.security.core.Authentication;

public abstract class ControllerBase {

    private final AccountService accountService;

    protected ControllerBase(AccountService accountService) {
        this.accountService = accountService;
    }

    protected AccountService getAccountService() {
        return accountService;
    }

    protected Account getAccount(Authentication authentication) {
        return getAccountService().findByLogin(authentication.getName()).orElseThrow();
    }
}
