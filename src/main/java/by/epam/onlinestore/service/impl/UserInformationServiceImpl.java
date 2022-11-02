package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.bean.UserInformation;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.DaoFactory;
import by.epam.onlinestore.dao.UserInformationDao;

import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.UserInformationService;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserInformationServiceImpl implements UserInformationService {

    private static final Logger logger = LogManager.getLogger(UserInformationServiceImpl.class);

    @Override
    public Optional<UserInformation> retrieveUserInformationById(long userInformationId) throws ServiceException {

        try {
            UserInformationDao userInformationDao = DaoFactory.getInstance().getUserInformationDao();
            Optional<UserInformation> userInformation;
            userInformation = userInformationDao.findUserInformationById(userInformationId);

            return userInformation;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve User information by ID", exception);
            throw new ServiceException(exception);
        }

    }

    @Override
    public List<UserInformation> retrieveUserInformationFromUsers(List<User> users) throws ServiceException {
        List<UserInformation> userInformation = new LinkedList<>();

        try {
            for (User user : users) {
                Optional<UserInformation> userInform = retrieveUserInformationById(user.getUserInformationId());
                if (userInform.isPresent()) {
                    if (!userInformation.contains(userInform.get())) {
                        userInformation.add(userInform.get());
                    }
                }
            }
        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve User information from users", exception);
            throw new ServiceException(exception);
        }

        return userInformation;
    }
}
