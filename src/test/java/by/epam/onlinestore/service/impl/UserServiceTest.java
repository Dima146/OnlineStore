package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.dao.connectionpool.ConnectionPool;
import by.epam.onlinestore.dao.connectionpool.ConnectionPoolException;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.UserService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolData();
    }

    @Test
    public void testLogin_ReturnUser_IfDataIsCorrect() throws ServiceException {
        Optional<User> actual = userService.logIn("vasya123@gmail.com", "vasya123");
        assertTrue(actual.isPresent());

    }

    @Test
    public void testRegistration_ReturnUser_IfDataIsCorrect() throws ServiceException {

        String email = "vasya123@gmail.com";
        String password = "vasya123";
        String name = "Василий";
        String surname = "Васильев";
        String patronymic = "Васильевич";
        String phone = "375330000000";

        assertTrue(userService.register(name, surname, patronymic, email, phone, password));
    }
}
