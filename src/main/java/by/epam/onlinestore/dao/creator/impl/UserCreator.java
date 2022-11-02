package by.epam.onlinestore.dao.creator.impl;

import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.dao.creator.ColumnLabel;
import by.epam.onlinestore.dao.creator.Creator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreator implements Creator<User> {

    @Override
    public User create(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong(ColumnLabel.USER_ID));
        user.setEmail(resultSet.getString(ColumnLabel.EMAIL));
        user.setPassword(resultSet.getString(ColumnLabel.PASSWORD));
        user.setRoleId(resultSet.getLong(ColumnLabel.ROLE_ID_FK));
        user.setUserInformationId(resultSet.getLong(ColumnLabel.USER_INFORMATION_ID_FK));

        return user;
    }
}
