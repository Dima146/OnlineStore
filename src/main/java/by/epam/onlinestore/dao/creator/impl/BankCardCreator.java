package by.epam.onlinestore.dao.creator.impl;

import by.epam.onlinestore.bean.BankCard;
import by.epam.onlinestore.dao.creator.ColumnLabel;
import by.epam.onlinestore.dao.creator.Creator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankCardCreator implements Creator<BankCard> {

    @Override
    public BankCard create(ResultSet resultSet) throws SQLException {
        BankCard bankCard = new BankCard();

        bankCard.setId(resultSet.getLong(ColumnLabel.BANK_CARD_ID));
        bankCard.setCardNumber(resultSet.getLong(ColumnLabel.CARD_NUMBER));
        bankCard.setExpirationYear(resultSet.getInt(ColumnLabel.EXPIRATION_YEAR));
        bankCard.setExpirationMonth(resultSet.getInt(ColumnLabel.EXPIRATION_MONTH));
        bankCard.setCardOwnerName(resultSet.getString(ColumnLabel.CARD_OWNER_NAME));
        bankCard.setCvvNumber(resultSet.getInt(ColumnLabel.CVV_NUMBER));
        bankCard.setBalance(resultSet.getDouble(ColumnLabel.BALANCE));
        bankCard.setUserId(resultSet.getLong(ColumnLabel.USER_ID_FK));

        return bankCard;
    }
}
