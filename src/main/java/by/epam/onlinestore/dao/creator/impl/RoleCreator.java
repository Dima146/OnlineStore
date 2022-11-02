package by.epam.onlinestore.dao.creator.impl;

import by.epam.onlinestore.bean.Role;
import by.epam.onlinestore.dao.creator.ColumnLabel;
import by.epam.onlinestore.dao.creator.Creator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleCreator implements Creator<Role> {

    @Override
    public Role create(ResultSet resultSet) throws SQLException {
        Role role = new Role();

        role.setId(resultSet.getLong(ColumnLabel.ROLE_ID));
        role.setRoleName(resultSet.getString(ColumnLabel.ROLE_NAME));

        return role;
    }
}
