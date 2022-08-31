package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class EmailValidator extends AbstractValidator {
    private static final String EMAIL =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    @Override
    protected String getRegex() {
        return EMAIL;
    }
}
