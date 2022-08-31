package by.epam.onlinestore.service.validator.impl;

import by.epam.onlinestore.service.validator.AbstractValidator;

public class PriceValidator extends AbstractValidator {
    private static final String PRICE = "^\\d+\\.\\d{0,2}$";

    @Override
    protected String getRegex() {
        return PRICE;
    }
}
