package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.OrderInformation;
import java.util.List;
import java.util.Optional;

public interface OrderInformationDao {

    /**
     * Receive OrderInformation from Database by id
     *
     * @param id - OrderInformation id
     * @return  OrderInformation
     */
    Optional<OrderInformation> findOrderInformationById(long id) throws DaoException;

    /**
     * Save OrderInformation into Database
     *
     * @param orderInformation to save
     */
    long saveOrderInformation(OrderInformation orderInformation) throws DaoException;

    /**
     * Receive UserOrder from Database by status
     *
     * @param status - UserOrder status
     * @return List of user orders
     */
    List<OrderInformation> findOrderInformationByStatus(String status) throws DaoException;

    /**
     * Update OrderInformation status in Database by id
     *
     * @param id     - OrderInformation id to update
     * @param status - OrderInformation status
     */
    void updateStatusById(long id, String status) throws DaoException;

}
