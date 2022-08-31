package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class CardNumberValidator extends AbstractValidator {
    private static final String CARD_NUMBER = "^[0-9]{16}$";

    @Override
    protected String getRegex() {
        return CARD_NUMBER;
    }
}
