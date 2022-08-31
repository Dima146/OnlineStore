package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.Role;
import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.bean.UserInformation;

import by.epam.onlinestore.dao.*;

import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.UserService;
import by.epam.onlinestore.service.validator.Validator;
import by.epam.onlinestore.service.validator.ValidatorFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String USER = "user";

    @Override
    public Optional<User> logIn(String email, String password) throws ServiceException {

        if (email == null || password == null) {
            return Optional.empty();
        }

        if (!isEmailValid(email)) {
            return Optional.empty();
        }

        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            return userDao.findUserByEmailAndPassword(email, password);

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to log in user");
            throw new ServiceException(exception);
        }

    }

    @Override
    public Optional<User> retrieveUserById(long userId) throws ServiceException {

        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            Optional<User> user;
            user = userDao.findUserById(userId);

            return user;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve User by ID");
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<User> retrieveUsersFromOrders(List<OrderFromUser> ordersFromUsers) throws ServiceException {
        List<User> userList = new ArrayList<>();

        try {
            for (OrderFromUser orderFromUser: ordersFromUsers) {
                Optional<User> user = retrieveUserById(orderFromUser.getUserId());
                if (user.isPresent()) {
                    if (!userList.contains(user.get())) {
                        userList.add(user.get());
                    }
                }
            }

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve users from orders");
            throw new ServiceException(exception);
        }
        return userList;
    }

    @Override
    public boolean register(String name, String surname, String patronymic, String email, String phoneNumber,
                            String password) throws ServiceException {

        if (name == null || surname == null || patronymic == null ||
                email == null || phoneNumber == null || password == null) {
            return false;
        }

        if (!(isEmailValid(email) && isUserInformationValid(name, surname, patronymic, phoneNumber))) {
            return false;
        }

        try {
            UserDao userDao = DaoFactory.getInstance().getUserDao();
            if (userDao.findUserByEmail(email).isPresent()) {
                return false;
            }
            RoleDao roleDao = DaoFactory.getInstance().getRoleDao();
            Optional<Role> role = roleDao.findRoleByName(USER);
            if (role.isEmpty()) {
                return false;
            }
            long phone = Long.parseLong(phoneNumber);
            UserInformationDao userInformationDao = DaoFactory.getInstance().getUserInformationDao();
            UserInformation userInformation = buildUserInformation(name, surname, patronymic, phone);
            long userInformationId = userInformationDao.saveUserInformation(userInformation);

            User user = buildUser(email, password, userInformationId, role.get().getId());
            userDao.saveUser(user);

            return true;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to register new user");
            throw new ServiceException(exception);
        }
    }


    private boolean isEmailValid(String email) {
        Validator validator = ValidatorFactory.getInstance().getEmailValidator();
        return validator.isValid(email);
    }

    private boolean isUserInformationValid(String name, String surname, String patronymic, String phone) {
        Validator nameValidator = ValidatorFactory.getInstance().getNameValidator();
        Validator phoneValidator = ValidatorFactory.getInstance().getPhoneValidator();

        return nameValidator.isValid(name) && nameValidator.isValid(surname) && nameValidator.isValid(patronymic) &&
                phoneValidator.isValid(phone);
    }

    private UserInformation buildUserInformation(String name, String surname, String patronymic, long phone) {
        UserInformation userInformation = new UserInformation();
        userInformation.setUserName(name);
        userInformation.setUserSurname(surname);
        userInformation.setUserPatronymicName(patronymic);
        userInformation.setUserPhoneNumber(phone);
        return userInformation;
    }

    private User buildUser(String email, String password, long userInformationId, long roleId) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUserInformationId(userInformationId);
        user.setRoleId(roleId);
        return user;
    }
}
