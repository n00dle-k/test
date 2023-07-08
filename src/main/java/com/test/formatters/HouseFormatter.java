package com.test.formatters;

import com.test.models.House;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class HouseFormatter implements Formatter<House> {
    @Override
    public House parse(String text, Locale locale) throws ParseException {
        final var t = new House();
        t.setId(Long.parseLong(text));
        return t;
    }

    @Override
    public String print(House object, Locale locale) {
        return object.getId().toString();
    }
}
