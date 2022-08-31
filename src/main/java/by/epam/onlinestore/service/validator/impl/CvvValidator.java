package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class CvvValidator extends AbstractValidator {
    private static final String CVV = "^[0-9]{3}$";

    @Override
    protected String getRegex() {
        return CVV;
    }
}
