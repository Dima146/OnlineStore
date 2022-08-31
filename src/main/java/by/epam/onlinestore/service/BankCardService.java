package by.epam.onlinestore.service;

public interface BankCardService {

    /**
     * Check bank card presence
     *
     * @param cardNumber        - bank card number
     * @param expirationMonth   - expiration month of bank card
     * @param expirationYear    - expiration year of bank card
     * @param cvvNumber         - cvv number of bank card
     * @return result of checkin
     */
    boolean bankCardPresenceCheck(long cardNumber, int expirationMonth, int expirationYear, int cvvNumber)
                                                                                                throws ServiceException;

    /**
     * Pay for order
     *
     * @param cardNumber        - bank card number
     * @param expirationMonth   - expiration month of bank card
     * @param expirationYear    - expiration year of bank card
     * @param cvvNumber         - cvv code of bank card
     * @param price             - order price
     * @return result of paying
     */
    boolean pay(long cardNumber, int expirationMonth, int expirationYear, int cvvNumber, double price)
                                                                                                throws ServiceException;

}
