package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.dao.*;
import by.epam.onlinestore.dao.mapper.RowMapperFactory;

import java.util.List;
import java.util.Optional;

public class OrderFromUserDaoImpl extends AbstractSqlExecutor<OrderFromUser> implements OrderFromUserDao {

    private static final String FIND_ORDER_FROM_USER_BY_ID = "SELECT * FROM " + TableLabel.ORDER_FROM_USER +
                                                                " WHERE order_from_user_id=?";

    private static final String DELETE_ORDER_FROM_USER_BY_ID = "DELETE FROM " + TableLabel.ORDER_FROM_USER +
                                                                " WHERE order_from_user_id=?";

    private static final String SAVE_ORDER_FROM_USER = "INSERT INTO " + TableLabel.ORDER_FROM_USER +
            " (product_id_fk, user_id_fk, purchase_quantity) VALUES (?, ?, ?)";

    private static final String FIND_ORDER_FROM_USER_BY_USER_ID = "SELECT * FROM " +
                        TableLabel.ORDER_FROM_USER + " WHERE user_id_fk=? ORDER BY order_from_user_id DESC";

    private static final String UPDATE_ORDER_INFORMATION = "UPDATE " + TableLabel.ORDER_FROM_USER +
                                                        " SET order_information_id_fk=? WHERE order_from_user_id=?";

    private static final String FIND_ORDER_FROM_USER_BY_ORDER_INFORMATION_ID =
            "SELECT * FROM " + TableLabel.ORDER_FROM_USER + " WHERE order_information_id_fk=?";

    private static final String FIND_ORDERS_FROM_USER_BY_USER_ID_WITHOUT_ORDER_INFORMATION = "SELECT * FROM " +
            TableLabel.ORDER_FROM_USER +
            " WHERE user_id_fk=? AND order_information_id_fk IS NULL ORDER BY order_from_user_id DESC";

    private static final String FIND_ORDERS_BY_USER_ID_AND_PRODUCT_ID_WITHOUT_ORDER_INFORMATION =
            "SELECT * FROM " + TableLabel.ORDER_FROM_USER + " WHERE user_id_fk=? AND product_id_fk=? AND order_information_id_fk IS NULL";


    public OrderFromUserDaoImpl() {
        super(RowMapperFactory.getInstance().getOrderFromUserMapper(), TableLabel.ORDER_FROM_USER);
    }

    @Override
    public Optional<OrderFromUser> findOrderFromUserById(long id) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_ORDER_FROM_USER_BY_ID, id);
    }

    @Override
    public long saveOrderFromUser(OrderFromUser orderFromUser) throws DaoException {

        return executeInsertSqlQuery(SAVE_ORDER_FROM_USER, orderFromUser.getProductId(), orderFromUser.getUserId(),
                orderFromUser.getPurchaseQuantity());
    }

    @Override
    public List<OrderFromUser> findOrderFromUserByUserId(long userId) throws DaoException {

        return executeSqlQuery(FIND_ORDER_FROM_USER_BY_USER_ID, userId);
    }

    @Override
    public List<OrderFromUser> findOrderFromUserByOrderInformationId(long orderInformationId) throws DaoException {

        return executeSqlQuery(FIND_ORDER_FROM_USER_BY_ORDER_INFORMATION_ID, orderInformationId);
    }

    @Override
    public List<OrderFromUser> findByUserWithoutOrderInformation(long userId) throws DaoException {

        return executeSqlQuery(FIND_ORDERS_FROM_USER_BY_USER_ID_WITHOUT_ORDER_INFORMATION, userId);
    }

    @Override
    public List<OrderFromUser> findByUserAndProductWithoutOrderInformation(long userId, long productId) throws DaoException {

        return executeSqlQuery(FIND_ORDERS_BY_USER_ID_AND_PRODUCT_ID_WITHOUT_ORDER_INFORMATION, userId, productId);
    }

    @Override
    public void updateOrderInformation(long id, long orderInformationId) throws DaoException {

        executeUpdateSqlQuery(UPDATE_ORDER_INFORMATION, orderInformationId, id);

    }

    @Override
    public void removeOrderFromUserById(long id) throws DaoException {

        executeUpdateSqlQuery(DELETE_ORDER_FROM_USER_BY_ID, id);
    }
}
