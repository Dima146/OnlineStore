package by.epam.onlinestore.dao.mapper.impl;

import by.epam.onlinestore.bean.Role;
import by.epam.onlinestore.dao.mapper.ColumnLabel;
import by.epam.onlinestore.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements Mapper<Role> {

    @Override
    public Role map(ResultSet resultSet) throws SQLException {
        Role role = new Role();

        role.setId(resultSet.getLong(ColumnLabel.ROLE_ID));
        role.setRoleName(resultSet.getString(ColumnLabel.ROLE_NAME));

        return role;
    }
}
