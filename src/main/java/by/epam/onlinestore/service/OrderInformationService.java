package by.epam.onlinestore.service;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.OrderInformation;
import java.util.List;
import java.util.Optional;

public interface OrderInformationService {

    /**
     * Retrieve OrderInformation by ID
     *
     * @param orderInformation - OrderInformation ID
     * @return OrderInformation
     */
    Optional<OrderInformation> retrieveOrderInformationById(long orderInformation) throws ServiceException;

    /**
     * Retrieve OrderInformation by status
     *
     * @param orderStatus - order status
     */
    List<OrderInformation> retrieveOrderInformationByStatus(String orderStatus) throws ServiceException;

    /**
     * Update status in OrderInformation by ID
     *
     * @param orderInformationId - OrderInformation ID
     * @param status             - order status
     */
    boolean updateStatusInOrderInformationById(long orderInformationId, String status) throws ServiceException;

    /**
     * Add new OrderInformation
     *
     * @param ordersFromUsers   - orders from User
     * @param deliveryAddress   - delivery address
     * @param deliveryDate      - delivery date
     * @return result of OrderInformation adding
     */
    boolean addNewOrderInformation(List<OrderFromUser> ordersFromUsers, String deliveryAddress, String deliveryDate,
                                   String cardholderName, String cvv, String cardNumber,
                                   String month, String year, double price) throws ServiceException;

    /**
     * Retrieve OrderInformation from orders from User
     *
     * @param ordersFromUser - List of orders from User
     * @return List of OrderInformation
     */
    List<OrderInformation> retrieveOrderInformationFromOrdersFromUser(List<OrderFromUser> ordersFromUser)
                                                                        throws ServiceException;
}

