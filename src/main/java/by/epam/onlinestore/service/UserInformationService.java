package by.epam.onlinestore.service;

import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.bean.UserInformation;
import java.util.List;
import java.util.Optional;

public interface UserInformationService {

    /**
     * Retrieve user information by ID
     *
     * @param userInformationId - user information ID
     * @return UserInformation
     */
    Optional<UserInformation> retrieveUserInformationById(long userInformationId) throws ServiceException;

    /**
     * Retrieve user information from users
     *
     * @param users - List of users
     * @return List of Users Information
     */
    List<UserInformation> retrieveUserInformationFromUsers(List<User> users) throws ServiceException;
}

