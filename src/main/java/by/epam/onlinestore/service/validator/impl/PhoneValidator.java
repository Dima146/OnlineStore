package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class PhoneValidator extends AbstractValidator {
    private static final String PHONE = "^[0-9]{10,15}$";

    @Override
    protected String getRegex() {
        return PHONE;
    }
}
