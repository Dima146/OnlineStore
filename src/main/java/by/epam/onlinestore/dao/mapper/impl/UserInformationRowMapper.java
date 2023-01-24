package by.epam.onlinestore.dao.mapper.impl;

import by.epam.onlinestore.bean.UserInformation;
import by.epam.onlinestore.dao.mapper.ColumnLabel;
import by.epam.onlinestore.dao.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInformationRowMapper implements RowMapper<UserInformation> {

    @Override
    public UserInformation create(ResultSet resultSet) throws SQLException {
        UserInformation userInformation = new UserInformation();

        userInformation.setId(resultSet.getLong(ColumnLabel.USER_INFORMATION_ID));
        userInformation.setUserName(resultSet.getString(ColumnLabel.USER_NAME));
        userInformation.setUserSurname(resultSet.getString(ColumnLabel.USER_SURNAME));
        userInformation.setUserPatronymicName(resultSet.getString(ColumnLabel.USER_PATRONYMIC_NAME));
        userInformation.setUserPhoneNumber(resultSet.getLong(ColumnLabel.USER_PHONE_NUMBER));

        return userInformation;
    }
}
