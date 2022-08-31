package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class MonthValidator extends AbstractValidator {
    private static final String MONTH = "^(0[1-9]|1[012])$";

    @Override
    protected String getRegex() {
        return MONTH;
    }
}
