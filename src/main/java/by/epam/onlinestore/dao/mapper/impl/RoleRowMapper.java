package by.epam.onlinestore.dao.mapper.impl;

import by.epam.onlinestore.bean.Role;
import by.epam.onlinestore.dao.mapper.ColumnLabel;
import by.epam.onlinestore.dao.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role create(ResultSet resultSet) throws SQLException {
        Role role = new Role();

        role.setId(resultSet.getLong(ColumnLabel.ROLE_ID));
        role.setRoleName(resultSet.getString(ColumnLabel.ROLE_NAME));

        return role;
    }
}
