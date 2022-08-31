package by.epam.onlinestore.service.validator;

import by.epam.onlinestore.service.validator.impl.EmailValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    AbstractValidator validator = new EmailValidator();
    private static final String CORRECT_EMAIL = "email@gmail.com";
    private static final String INCORRECT_EMAIL = "email@gmailcom";

    @Test
    public void testEmailValidator_ReturnTrueIsCorrect() {
        boolean result = validator.isValid(CORRECT_EMAIL);
        assertTrue(result);
    }

    @Test
    public void testEmailValidator_ReturnFalseIsNotCorrect() {
        boolean result = validator.isValid(INCORRECT_EMAIL);
        assertFalse(result);
    }
}
