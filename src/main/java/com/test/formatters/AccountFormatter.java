package com.test.formatters;

import com.test.models.Account;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AccountFormatter implements Formatter<Account> {
    @Override
    public Account parse(String text, Locale locale) throws ParseException {
        final var t = new Account();
        t.setId(Long.parseLong(text));
        return t;
    }

    @Override
    public String print(Account object, Locale locale) {
        return object.getId().toString();
    }
}
