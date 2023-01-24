package by.epam.onlinestore.dao.mapper;

import by.epam.onlinestore.bean.Bean;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Functional interface used for mapping data from
 * ResultSet to Bean
 * @param <T> - Bean object mapped from data source
 */

@FunctionalInterface
public interface RowMapper<T extends Bean> {

    T create(ResultSet resultSet) throws SQLException;
}
