package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.dao.AbstractSqlExecutor;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.TableLabel;
import by.epam.onlinestore.dao.UserDao;
import by.epam.onlinestore.dao.mapper.RowMapperFactory;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractSqlExecutor<User> implements UserDao {

    private static final String FIND_USER_BY_ID = "SELECT * FROM " + TableLabel.USER + " WHERE user_id=?";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD =
            "SELECT * FROM " + TableLabel.USER + " WHERE email=? and password=SHA1(?)";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM " + TableLabel.USER + " WHERE email=?";
    private static final String SAVE_USER =
            "INSERT INTO " + TableLabel.USER + " (email, password, role_id_fk, user_information_id_fk) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER_BY_ID = "DELETE FROM " + TableLabel.USER + " WHERE user_id=?";

    private static final String FIND_ALL_USERS = "SELECT * FROM " + TableLabel.USER;

    public UserDaoImpl() {
        super(RowMapperFactory.getInstance().getUserMapper(), TableLabel.USER);
    }


    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_USER_BY_EMAIL_AND_PASSWORD, email, password);
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_USER_BY_EMAIL, email);
    }

    @Override
    public Optional<User> findUserById(long id) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_USER_BY_ID, id);
    }

    @Override
    public long saveUser(User user) throws DaoException {

        return executeInsertSqlQuery(SAVE_USER, user.getEmail(), user.getPassword(), user.getRoleId(), user.getUserInformationId());
    }

    @Override
    public void removeUserById(long id) throws DaoException {

        executeUpdateSqlQuery(DELETE_USER_BY_ID, id);

    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        return executeSqlQuery(FIND_ALL_USERS);
    }
}
