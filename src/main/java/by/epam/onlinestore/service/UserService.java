package by.epam.onlinestore.service;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Log in user
     *
     * @param email    - User email
     * @param password - User password
     * @return User
     */
    Optional<User> logIn(String email, String password) throws ServiceException;

    /**
     * Retrieve User by ID
     *
     * @param userId - User ID
     * @return User
     */
    Optional<User> retrieveUserById(long userId) throws ServiceException;

    /**
     * Retrieve users from OrderFromUsers
     *
     * @param ordersFromUsers - List of OrdersFromUsers
     * @return List of users
     */
    List<User> retrieveUsersFromOrders(List<OrderFromUser> ordersFromUsers) throws ServiceException;

    /**
     * Register new User
     *
     * @param name        - username
     * @param surname     - User surname
     * @param patronymic  - User patronymic name
     * @param email       - User email
     * @param phoneNumber - User phone number
     * @param password    - User password
     * @return registration result
     */
    boolean register(String name, String surname, String patronymic,
                     String email, String phoneNumber, String password) throws ServiceException;

}

