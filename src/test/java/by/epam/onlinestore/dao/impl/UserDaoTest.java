package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.UserDao;
import by.epam.onlinestore.dao.connectionpool.ConnectionPool;
import by.epam.onlinestore.dao.connectionpool.ConnectionPoolException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoTest {

    UserDao userDao = new UserDaoImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolData();
    }

    @Test
    public void testLogin_ReturnUser_IfDataIsCorrect() throws DaoException {
        Optional<User> actual = userDao.findUserByEmailAndPassword("vasya123@gmail.com", "vasya123");
        assertTrue(actual.isPresent());
    }
}
