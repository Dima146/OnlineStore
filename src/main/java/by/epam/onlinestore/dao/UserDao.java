package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    /**
     * Receive User from Database by email and password
     *
     * @param email - User email
     * @param password - User password
     * @return  User
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException ;

    /**
     * Receive User from Database by email
     *
     * @param email - User email
     * @return User
     */
    Optional<User> findUserByEmail(String email) throws DaoException ;

    /**
     * Receive User from Database by id
     *
     * @param id - User id
     * @return  User
     */
    Optional<User> findUserById(long id) throws DaoException ;

    /**
     * Save User into Database
     *
     * @param user to save
     */
    long saveUser(User user) throws DaoException ;

    /**
     * Remove User from Database by id
     *
     * @param id - User id
     */
    void removeUserById(long id) throws DaoException ;

    List<User> findAllUsers() throws DaoException;
}
