package by.epam.onlinestore.service;

import by.epam.onlinestore.bean.Role;
import java.util.Optional;

public interface RoleService {

    /**
     * Retrieve user role by ID
     *
     * @param roleId - ID role
     * @return Role
     */
    Optional<Role> retrieveRoleById(long roleId) throws ServiceException;
}

