package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.Role;
import by.epam.onlinestore.dao.AbstractSqlExecutor;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.RoleDao;
import by.epam.onlinestore.dao.TableLabel;
import by.epam.onlinestore.dao.creator.CreatorFactory;

import java.util.Optional;

public class RoleDaoImpl extends AbstractSqlExecutor<Role> implements RoleDao {

    private static final String SAVE_ROLE = "INSERT INTO " + TableLabel.ROLE + " (role_name) VALUES (?)";
    private static final String FIND_ROLE_BY_NAME = "SELECT * FROM " + TableLabel.ROLE + " WHERE role_name=?";
    private static final String FIND_ROLE_BY = "SELECT * FROM " + TableLabel.ROLE + " WHERE role_id=?";

    public RoleDaoImpl() {
        super(CreatorFactory.getInstance().getRoleMapper(), TableLabel.ROLE);

    }

    @Override
    public Optional<Role> findRoleByName(String name) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_ROLE_BY_NAME, name);
    }

    @Override
    public Optional<Role> findRoleById(long id) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_ROLE_BY, id);
    }

    @Override
    public long saveRole(Role role) throws DaoException {

        return executeInsertSqlQuery(SAVE_ROLE, role.getRoleName());
    }
}
