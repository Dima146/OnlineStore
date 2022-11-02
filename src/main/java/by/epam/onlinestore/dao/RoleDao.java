package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.Role;
import java.util.Optional;

public interface RoleDao {

    /**
     * Receive role from Database by name
     *
     * @param name - Role name
     * @return Role
     */
    Optional<Role> findRoleByName(String name) throws DaoException;

    /**
     * Receive Role from Database by id
     *
     * @param id - Role id
     * @return  Role
     */
    Optional<Role> findRoleById(long id) throws DaoException ;

    /**
     * Save Role into Database
     *
     * @param role to save
     */
    long saveRole(Role role) throws DaoException;
}
