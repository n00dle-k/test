package com.test.services;

import com.test.models.Account;
import com.test.repo.AccountRepository;
import com.test.security.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.io.IOException;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var oAccount = findByLogin(username);
        if (oAccount.isPresent()) {
            return new AccountDetails(oAccount.get());
        } else {
            throw new UsernameNotFoundException("There is no user with username:" + username);
        }
    }

    @Override
    public Optional<Account> findByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    @Override
    public void createNewUser(Account account) throws IOException {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public void validateNewAccount(Object target, Errors errors) {
        final var account = (Account) target;
        if (findByLogin(account.getLogin()).isPresent()) {
            errors.rejectValue("userName", "Current username is already in use...");
        }
    }
}
