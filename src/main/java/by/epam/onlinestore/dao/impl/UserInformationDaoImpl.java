package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.UserInformation;
import by.epam.onlinestore.dao.AbstractSqlExecutor;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.TableLabel;
import by.epam.onlinestore.dao.UserInformationDao;
import by.epam.onlinestore.dao.creator.CreatorFactory;

import java.util.Optional;

public class UserInformationDaoImpl extends AbstractSqlExecutor<UserInformation> implements UserInformationDao {

    private static final String SAVE_USER_INFORMATION = "INSERT INTO " + TableLabel.USER_INFORMATION +
            " (user_name, user_surname, user_patronymic_name, user_phone_number) VALUES (?, ?, ?, ?)";

    private static final String FIND_USER_INFORMATION_BY_ID = "SELECT * FROM " + TableLabel.USER_INFORMATION + " WHERE user_information_id=?";


    public UserInformationDaoImpl() {
        super(CreatorFactory.getInstance().getUserInformationMapper(), TableLabel.USER_INFORMATION);
    }

    @Override
    public Optional<UserInformation> findUserInformationById(long id) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_USER_INFORMATION_BY_ID, id);
    }

    @Override
    public long saveUserInformation(UserInformation userInformation) throws DaoException {

        return executeInsertSqlQuery(SAVE_USER_INFORMATION,
                userInformation.getUserName(),
                userInformation.getUserSurname(),
                userInformation.getUserPatronymicName(),
                userInformation.getUserPhoneNumber());
    }
}

