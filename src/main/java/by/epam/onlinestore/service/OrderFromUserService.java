package by.epam.onlinestore.service;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.OrderInformation;
import java.util.List;

public interface OrderFromUserService {

    /**
     * Retrieve orders from User by User ID where OrderInformation = null
     *
     * @param userId - User ID
     * @return List of orders from User
     */
    List<OrderFromUser> retrieveByUserIdWithoutOrderInformation(long userId) throws ServiceException;

    /**
     * Retrieve orders from User by User ID
     *
     * @param userId - User ID
     * @return List of orders from User
     */
    List<OrderFromUser> retrieveOrdersFromUserByUserId(long userId) throws ServiceException;

    /**
     * Retrieve orders from User by User ID where OrderInformation = null and status = true
     *
     * @param userId - User ID
     * @return List of orders from User
     */
    List<OrderFromUser> retrieveOrdersFromUserByUserWhereProductStatusTrue(long userId) throws ServiceException;

    /**
     * Retrieve orders from User by OrderInformation
     *
     * @param orderInformationId - OrderInformation ID
     * @return List of orders from User
     */
    List<OrderFromUser> retrieveOrdersFromUserByOrderInformationId(long orderInformationId) throws ServiceException;

    /**
     * Remove order from User by ID
     *
     * @param orderId - orders from User ID
     * @return result of removing
     */
    boolean removeOrderFromUserById(long orderId) throws ServiceException;

    /**
     * Add new order
     *
     * @param userId           - User ID
     * @param productId        - Product ID
     * @param purchaseQuantity - quantity of bought products
     * @return result of order adding
     */
    boolean addNewOrder(long userId, long productId, int purchaseQuantity) throws ServiceException;

    /**
     * Calculate cost of orders from User
     *
     * @param ordersFromUser - List of orders from User
     * @return cost
     */
    double calculateCost(List<OrderFromUser> ordersFromUser) throws ServiceException;

    /**
     * Retrieve User orders from OrderInformation
     *
     * @param orderInformation - List of orders Information
     * @return List of orders from User
     */
    List<OrderFromUser> retrieveOrdersFromUserFromOrderInformation(List<OrderInformation> orderInformation)
                                                                    throws ServiceException;
}

