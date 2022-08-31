package by.epam.onlinestore.service.validator;

import by.epam.onlinestore.service.validator.impl.CardNumberValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankCardValidatorTest {

    private final AbstractValidator validator = new CardNumberValidator();
    private static final String CORRECT_CARD_NUMBER = "1122334455667788";
    private static final String INCORRECT_CARD_NUMBER = "112233445566778";

    @Test
    public void testCardNumberValidator_ReturnTrueIsCorrect() {
        boolean result = validator.isValid(CORRECT_CARD_NUMBER);
        assertTrue(result);
    }

    @Test
    public void testCardNumberValidator_ReturnFalseIsNotCorrect() {
        boolean result = validator.isValid(INCORRECT_CARD_NUMBER);
        assertFalse(result);
    }
}
