package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class YearValidator extends AbstractValidator {
    private static final String YEAR = "^202[2-9]{1}$";

    @Override
    protected String getRegex() {
        return YEAR;
    }
}
