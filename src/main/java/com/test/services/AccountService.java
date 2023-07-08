package com.test.services;

import com.test.models.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;

import java.io.IOException;
import java.util.Optional;

public interface AccountService extends UserDetailsService {
    Optional<Account> findByLogin(String userName);

    void createNewUser(Account account) throws IOException;

    void validateNewAccount(Object target, Errors errors);
}
