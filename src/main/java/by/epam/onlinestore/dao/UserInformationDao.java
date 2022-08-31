package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.UserInformation;
import java.util.Optional;

public interface UserInformationDao {

    /**
     * Receive UserInformation from Database by id
     *
     * @param id - UserInformation id
     * @return  UserInformation
     */
    Optional<UserInformation> findUserInformationById(long id) throws DaoException ;

    /**
     * Save UserInformation into Database
     *
     * @param userInformation to save
     */
    long saveUserInformation(UserInformation userInformation) throws DaoException ;
}
