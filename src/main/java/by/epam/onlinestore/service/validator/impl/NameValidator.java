package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class NameValidator extends AbstractValidator {
    private static final String NAME = "^[A-ZА-Я]{1}[A-zА-я]{2,20}$";

    @Override
    protected String getRegex() {
        return NAME;
    }
}
