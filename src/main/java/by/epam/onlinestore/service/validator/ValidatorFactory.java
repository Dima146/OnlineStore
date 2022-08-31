package by.epam.onlinestore.service.validator;

import by.epam.onlinestore.service.validator.impl.*;

public class ValidatorFactory {

    private static final ValidatorFactory INSTANCE = new ValidatorFactory();

    public static ValidatorFactory getInstance() {
        return INSTANCE;
    }

    private ValidatorFactory() {
    }

    public YearValidator getYearValidator() {
        return new YearValidator();
    }

    public PriceValidator getPriceValidator() {
        return new PriceValidator();
    }

    public PhoneValidator getPhoneValidator() {
        return new PhoneValidator();
    }

    public NameValidator getNameValidator() {
        return new NameValidator();
    }

    public MonthValidator getMonthValidator() {
        return new MonthValidator();
    }

    public IdValidator getIdValidator() {
        return new IdValidator();
    }

    public EmailValidator getEmailValidator() {
        return new EmailValidator();
    }

    public CvvValidator getCvvValidator() {
        return new CvvValidator();
    }

    public CardNumberValidator getCardNumberValidator() {
        return new CardNumberValidator();
    }
}
