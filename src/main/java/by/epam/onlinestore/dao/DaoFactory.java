package by.epam.onlinestore.dao;

import by.epam.onlinestore.dao.impl.*;

public class DaoFactory {

    private final UserDao userDao = new UserDaoImpl();
    private final UserInformationDao userInformationDao = new UserInformationDaoImpl();
    private final RoleDao roleDao = new RoleDaoImpl();
    private final ProductCategoryDao productCategoryDao = new ProductCategoryDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();
    private final OrderInformationDao orderInformationDao = new OrderInformationDaoImpl();
    private final OrderFromUserDao orderFromUserDao = new OrderFromUserDaoImpl();
    private final BankCardDao bankCardDao = new BankCardDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return Holder.INSTANCE;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserInformationDao getUserInformationDao() {
        return userInformationDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public OrderInformationDao getOrderInformationDao() {
        return orderInformationDao;
    }

    public OrderFromUserDao getOrderFromUserDao() {
        return orderFromUserDao;
    }

    public BankCardDao getBankCardDao() {
        return bankCardDao;
    }

    private static class Holder {
        private static final DaoFactory INSTANCE = new DaoFactory();
    }
}
