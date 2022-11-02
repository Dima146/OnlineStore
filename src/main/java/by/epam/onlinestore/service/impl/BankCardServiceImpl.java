package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.BankCard;
import by.epam.onlinestore.dao.BankCardDao;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.DaoFactory;

import by.epam.onlinestore.service.BankCardService;
import by.epam.onlinestore.service.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class BankCardServiceImpl implements BankCardService {

    private static final Logger logger = LogManager.getLogger(BankCardServiceImpl.class);

    @Override
    public boolean bankCardPresenceCheck(long cardNumber, int expirationMonth, int expirationYear, int cvvNumber)
            throws ServiceException {

        Optional<BankCard> bankCard;

        try {
            BankCardDao bankCardDao = DaoFactory.getInstance().getBankCardDao();
            bankCard = bankCardDao.findBankCardByNumber(cardNumber);
            if (bankCard.isPresent()) {
                if (isBankCardInformationCorrect(bankCard.get(), cardNumber, expirationMonth,
                        expirationYear, cvvNumber)) {

                    return true;
                }
            }
        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to check bank card", exception);
            throw new ServiceException(exception);
        }

        return false;
    }

    @Override
    public boolean pay(long cardNumber, int expirationMonth, int expirationYear, int cvvNumber, double price)
            throws ServiceException {

        if (!bankCardPresenceCheck(cardNumber, expirationMonth, expirationYear, cvvNumber)) {
            return false;
        }

        Optional<BankCard> bankCard;

        try {
            BankCardDao bankCardDao = DaoFactory.getInstance().getBankCardDao();
            bankCard = bankCardDao.findBankCardByNumber(cardNumber);
            if (bankCard.isPresent()) {
                if (!checkMoneyPresence(bankCard.get().getBalance(), price)) {
                    return false;
                }


            double newBalance = calculateNewBalance(bankCard.get().getBalance(), price);
            double scale = Math.pow(10, 2);
            newBalance = Math.ceil(newBalance * scale) / scale;
            bankCardDao.updateBalanceByCardNumber(cardNumber, newBalance);
            return true;
            }

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to pay", exception);
            throw new ServiceException(exception);
        }

        return false;
    }

    private boolean isBankCardInformationCorrect(BankCard bankCard, long cardNumber, int expirationMonth,
                                                 int expirationYear, int cvv) {

        if (bankCard.getCardNumber() != cardNumber) {
            return false;
        }

        if (bankCard.getExpirationMonth() != expirationMonth) {
            return false;
        }

        if (bankCard.getExpirationYear() != expirationYear) {
            return false;
        }

        if (bankCard.getCvvNumber() != cvv) {
            return false;
        }

        return true;
    }

    private double calculateNewBalance(double balance, double price) {
        return balance - price;
    }

    private boolean checkMoneyPresence(double balance, double price) {
        return price <= balance;
    }
}
