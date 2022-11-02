package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.Role;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.DaoFactory;

import by.epam.onlinestore.dao.RoleDao;
import by.epam.onlinestore.service.RoleService;
import by.epam.onlinestore.service.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    @Override
    public Optional<Role> retrieveRoleById(long roleId) throws ServiceException {

        Optional<Role> userRole;
        try {
            RoleDao roleDao = DaoFactory.getInstance().getRoleDao();
            userRole = roleDao.findRoleById(roleId);

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve User role by ID", exception);
            throw new ServiceException(exception);
        }
        return userRole;
    }
}
