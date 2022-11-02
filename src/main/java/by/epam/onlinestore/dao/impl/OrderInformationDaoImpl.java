package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.OrderInformation;
import by.epam.onlinestore.dao.AbstractSqlExecutor;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.OrderInformationDao;
import by.epam.onlinestore.dao.TableLabel;
import by.epam.onlinestore.dao.creator.CreatorFactory;

import java.util.List;
import java.util.Optional;

public class OrderInformationDaoImpl extends AbstractSqlExecutor<OrderInformation> implements OrderInformationDao {

    private static final String FIND_ORDER_INFORMATION_BY_STATUS = "SELECT * FROM " + TableLabel.ORDER_INFORMATION +
                                                                    " WHERE order_status=?";

    private static final String UPDATE_ORDER_INFORMATION_STATUS_BY_ID = "UPDATE " + TableLabel.ORDER_INFORMATION +
                                                                        " SET order_status=? WHERE user_order_id=?";

    private static final String SAVE_ORDER_INFORMATION = "INSERT INTO " + TableLabel.ORDER_INFORMATION +
                        " (delivery_address, order_date, delivery_date, order_status) VALUES (?, ?, ?, ?)";

    private static final String FIND_ORDER_INFORMATION_BY_ID = "SELECT * FROM " + TableLabel.ORDER_INFORMATION +
                                                                " WHERE user_order_id=?";

    public OrderInformationDaoImpl() {
        super(CreatorFactory.getInstance().getOrderInformationMapper(), TableLabel.ORDER_INFORMATION);
    }

    @Override
    public Optional<OrderInformation> findOrderInformationById(long id) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_ORDER_INFORMATION_BY_ID, id);
    }

    @Override
    public long saveOrderInformation(OrderInformation orderInformation) throws DaoException {

        return executeInsertSqlQuery(SAVE_ORDER_INFORMATION, orderInformation.getDeliveryAddress(),
                                    orderInformation.getOrderDate(), orderInformation.getDeliveryDate(),
                                    orderInformation.getOrderStatus());
    }

    @Override
    public List<OrderInformation> findOrderInformationByStatus(String status) throws DaoException {

        return executeSqlQuery(FIND_ORDER_INFORMATION_BY_STATUS, status);
    }

    @Override
    public void updateStatusById(long id, String status) throws DaoException {

        executeUpdateSqlQuery(UPDATE_ORDER_INFORMATION_STATUS_BY_ID, status, id);

    }
}
