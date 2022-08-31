package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.BankCard;
import java.util.Optional;

public interface BankCardDao {

    /**
     * Save BankCard into Database
     *
     * @param bankCard - BankCard to save
     */
    long saveBankCard(BankCard bankCard) throws DaoException;

    /**
     * Receive BankCard from Database by card number
     *
     * @param cardNumber - BankCard number
     * @return BankCard
     */
    Optional<BankCard> findBankCardByNumber(long cardNumber) throws DaoException;

    /**
     * Update balance in Database by BankCard number
     *
     * @param cardNumber - BankCard number to update
     * @param newBalance - new balance
     */
    void updateBalanceByCardNumber(long cardNumber, double newBalance) throws DaoException;
}
