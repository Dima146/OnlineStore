package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class IdValidator extends AbstractValidator {
    private static final String ID = "(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])";

    @Override
    protected String getRegex() {
        return ID;
    }
}
