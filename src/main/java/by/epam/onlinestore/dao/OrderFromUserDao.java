package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.OrderFromUser;
import java.util.List;
import java.util.Optional;

public interface OrderFromUserDao {

    /**
     * Receive OrderFromUser from Database by id
     *
     * @param id - OrderFromUser id
     * @return  OrderFromUser
     */
    Optional<OrderFromUser> findOrderFromUserById(long id) throws DaoException;

    /**
     * Save OrderFromUser into Database
     *
     * @param orderFromUser to save
     */
    long saveOrderFromUser(OrderFromUser orderFromUser) throws DaoException;

    /**
     * Receive OrderFromUser from Database by userId
     *
     * @param userId - User id
     * @return List of OrdersFromUser
     */
    List<OrderFromUser> findOrderFromUserByUserId(long userId) throws DaoException;

    /**
     * Receive OrderFromUser from Database by OrderInformationId
     *
     * @param userFromOrderId - OrderInformationId
     * @return List of OrdersFromUser
     */
    List<OrderFromUser> findOrderFromUserByOrderInformationId(long userFromOrderId) throws DaoException;

    /**
     * Receive OrderFromUser from Database by userId where OrderInformation = null
     *
     * @param userId - User id
     * @return List of OrdersFromUser
     */
    List<OrderFromUser> findByUserWithoutOrderInformation(long userId) throws DaoException;

    /**
     * Receive OrderFromUser  from Database by userId and productId where OrderInformation = null
     *
     * @param userId    - User id
     * @param productId - Product id
     * @return List of OrdersFromUser
     */
    List<OrderFromUser> findByUserAndProductWithoutOrderInformation(long userId, long productId) throws DaoException;

    /**
     * Update OrderFromUser in Database by OrderFromUserId and userInformationId
     *
     * @param id                 - OrderFromUser id to update
     * @param orderInformationId - OrderInformation id which will be updated
     */
    void updateOrderInformation(long id, long orderInformationId) throws DaoException;

    /**
     * Remove OrderFromUser from Database by OrderFromUSer id
     * @param id - OrderFromUser id
     */

    void removeOrderFromUserById(long id) throws DaoException;
}
