package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.Bean;
import by.epam.onlinestore.dao.connectionpool.ConnectionPool;
import by.epam.onlinestore.dao.connectionpool.ConnectionPoolException;
import by.epam.onlinestore.dao.mapper.RowMapper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AbstractSqlExecutor<T extends Bean> {
    private static final Logger logger = LogManager.getLogger(AbstractSqlExecutor.class);
    private final String tableName;
    private final RowMapper<T> tRowMapper;

    public AbstractSqlExecutor(RowMapper<T> tRowMapper, String tableName) {
        this.tRowMapper = tRowMapper;
        this.tableName = tableName;
    }
    protected List<T> executeSqlQuery(String query, Object... params) throws DaoException {
        List<T> beans;
        try (PreparedStatement preparedStatement = createStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            beans = createEntitiesList(resultSet);

        }catch (SQLException exception) {
            logger.log(Level.ERROR,"Impossible to execute SQL query", exception );
            throw new DaoException(exception);
        }

        return beans;

    }

    protected Optional<T> executeSqlQueryForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeSqlQuery(query, params);
        if (items.isEmpty()) {
            return Optional.empty();
        }

        if (!(items.size() == 1)) {
            return Optional.empty();
        }

        return Optional.of(items.get(0));
    }

    protected long executeInsertSqlQuery(String query, Object... params) throws DaoException {
        long result = 0;
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                result = resultSet.getLong(1);
            }
        } catch (SQLException exception) {
            logger.log(Level.ERROR, "Impossible to execute insert query", exception);
            throw new DaoException(exception);
        }
        return result;
    }

    protected void executeUpdateSqlQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException exception) {
            logger.log(Level.ERROR," Impossible to execute update query", exception);
            throw new DaoException(exception);
        }
    }

    private PreparedStatement createStatement(String sqlQuery, Object ... params) throws DaoException {
        Connection connection;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            ConnectionPool.getInstance().releaseConnection(connection);

            return preparedStatement;

        } catch (SQLException exception) {
            logger.log(Level.ERROR, "Impossible to create Statement", exception);
            throw new DaoException(exception);
        } catch (ConnectionPoolException exception) {
            throw new DaoException("Impossible to take connection", exception);
        }
    }

    private List<T> createEntitiesList(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T entity = tRowMapper.create(resultSet);
                entities.add(entity);
            }
        } catch (SQLException exception) {
            logger.log(Level.ERROR, "Impossible to create entity list", exception);
            throw new DaoException(exception);
        }
        return entities;
    }

}

