package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.BankCard;
import by.epam.onlinestore.dao.AbstractSqlExecutor;
import by.epam.onlinestore.dao.BankCardDao;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.TableLabel;
import by.epam.onlinestore.dao.creator.CreatorFactory;

import java.util.Optional;

public class BankCardDaoImpl extends AbstractSqlExecutor<BankCard> implements BankCardDao {
    private static final String FIND_BANK_CARD_BY_NUMBER = "SELECT * FROM " + TableLabel.BANK_CARD + " WHERE card_number=?";
    private static final String SAVE_BANK_CARD = "INSERT INTO " + TableLabel.BANK_CARD +
            " (card_number, expiration_year, expiration_month, card_owner, balance, cvv_number) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_BALANCE_BY_CARD_NUMBER = "UPDATE " + TableLabel.BANK_CARD + " SET balance=? WHERE card_number=?";


    public BankCardDaoImpl() {
        super(CreatorFactory.getInstance().getBankCardMapper(), TableLabel.BANK_CARD);
    }


    @Override
    public long saveBankCard(BankCard bankCard) throws DaoException {

        return executeInsertSqlQuery(SAVE_BANK_CARD, bankCard.getCardNumber(), bankCard.getExpirationYear(),
                bankCard.getExpirationMonth(), bankCard.getCardOwnerName(), bankCard.getBalance(), bankCard.getCvvNumber());
    }

    @Override
    public Optional<BankCard> findBankCardByNumber(long cardNumber) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_BANK_CARD_BY_NUMBER, cardNumber);
    }

    @Override
    public void updateBalanceByCardNumber(long cardNumber, double newBalance) throws DaoException {

        executeUpdateSqlQuery(UPDATE_BALANCE_BY_CARD_NUMBER, newBalance, cardNumber);

    }
}
