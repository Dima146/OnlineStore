package by.epam.onlinestore.service.validator;

public interface Validator {

    /**
     * Validate information
     *
     * @param expression - string for validation
     * @return validation result
     */
    boolean isValid(String expression);
}


