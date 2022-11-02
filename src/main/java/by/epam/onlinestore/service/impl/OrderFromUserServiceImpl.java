package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.OrderInformation;
import by.epam.onlinestore.bean.Product;

import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.DaoFactory;
import by.epam.onlinestore.dao.OrderFromUserDao;
import by.epam.onlinestore.dao.ProductDao;

import by.epam.onlinestore.service.OrderFromUserService;
import by.epam.onlinestore.service.ProductService;
import by.epam.onlinestore.service.ServiceException;

import by.epam.onlinestore.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderFromUserServiceImpl implements OrderFromUserService {

    private static final Logger logger = LogManager.getLogger(OrderFromUserServiceImpl.class);

    @Override
    public List<OrderFromUser> retrieveByUserIdWithoutOrderInformation(long userId) throws ServiceException {

        try {
            OrderFromUserDao orderFromUserDao = DaoFactory.getInstance().getOrderFromUserDao();
            List<OrderFromUser> orderFromUserList;
            orderFromUserList = orderFromUserDao.findByUserWithoutOrderInformation(userId);

            return orderFromUserList;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve OrderFromUser by User ID without OrderInformation", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<OrderFromUser> retrieveOrdersFromUserByUserId(long userId) throws ServiceException {

        try {
            OrderFromUserDao orderFromUserDao = DaoFactory.getInstance().getOrderFromUserDao();
            List<OrderFromUser> orderFromUserList;
            orderFromUserList = orderFromUserDao.findOrderFromUserByUserId(userId);

            return orderFromUserList;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve OrderFromUser by User ID", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<OrderFromUser> retrieveOrdersFromUserByUserWhereProductStatusTrue(long userId) throws ServiceException {

        try {
            List<OrderFromUser> orderFromUserList = retrieveByUserIdWithoutOrderInformation(userId);

            ProductService productService = ServiceFactory.getInstance().getProductService();

            Iterator<OrderFromUser> orderFromUserIterator = orderFromUserList.iterator();
            while (orderFromUserIterator.hasNext()) {
                OrderFromUser nextOrderFromUser = orderFromUserIterator.next();
                Optional<Product> product = productService.retrieveProductById(nextOrderFromUser.getProductId());
                if (product.isPresent()) {
                    if (!product.get().isProductStatus()) {
                        orderFromUserIterator.remove();
                    }
                }
            }
            return orderFromUserList;

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve orders from User by User where product status = true", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<OrderFromUser> retrieveOrdersFromUserByOrderInformationId(long orderInformationId) throws ServiceException {


        try {
            OrderFromUserDao orderFromUserDao = DaoFactory.getInstance().getOrderFromUserDao();
            List<OrderFromUser> orderFromUserList;
            orderFromUserList = orderFromUserDao.findOrderFromUserByOrderInformationId(orderInformationId);

            return orderFromUserList;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve orders from User by order information ID", exception);
            throw new ServiceException(exception);
        }

    }

    @Override
    public boolean removeOrderFromUserById(long orderId) throws ServiceException {

        try {
            OrderFromUserDao orderFromUserDao = DaoFactory.getInstance().getOrderFromUserDao();
            Optional<OrderFromUser> orderFromUser = orderFromUserDao.findOrderFromUserById(orderId);
            if (!orderFromUser.isPresent()) {
                return false;
            }
            orderFromUserDao.removeOrderFromUserById(orderId);

            return true;

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to remove order by order from user ID", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean addNewOrder(long userId, long productId, int purchaseQuantity) throws ServiceException {

        try {
            OrderFromUserDao orderFromUserDao = DaoFactory.getInstance().getOrderFromUserDao();
            List<OrderFromUser> availableOrdersFromUserList =
                    orderFromUserDao.findByUserAndProductWithoutOrderInformation(userId, productId);

            if (!(availableOrdersFromUserList.size() == 0)) {
                return false;
            }

            OrderFromUser orderFromUser = buildOrder(userId, productId, purchaseQuantity);
            orderFromUserDao.saveOrderFromUser(orderFromUser);

            return true;

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to add new order", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public double calculateCost(List<OrderFromUser> ordersFromUser) throws ServiceException {
        double cost = 0.0;

        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            for (OrderFromUser orderFromUser : ordersFromUser) {
                long productId = orderFromUser.getProductId();

                Optional<Product> product = productDao.findProductById(productId);
                if (product.isPresent()) {
                    cost = cost + product.get().getProductPrice() * orderFromUser.getPurchaseQuantity();
                }
            }
        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to calculate cost", exception);
            throw new ServiceException(exception);
        }
        return cost;
    }

    @Override
    public List<OrderFromUser> retrieveOrdersFromUserFromOrderInformation(List<OrderInformation> ordersInformation)
            throws ServiceException {

        List<OrderFromUser> orderFromUserList = new LinkedList<>();

        for (OrderInformation orderInformation : ordersInformation ) {
            List<OrderFromUser> orderFromUsers = retrieveOrdersFromUserByOrderInformationId(orderInformation.getId());
            if (!orderFromUsers.isEmpty()) {
                orderFromUserList.addAll(orderFromUsers);
            }
        }

        return orderFromUserList;
    }

    private OrderFromUser buildOrder(long userId, long productId, int purchaseQuantity) {

        OrderFromUser orderFromUser = new OrderFromUser();
        orderFromUser.setUserId(userId);
        orderFromUser.setProductId(productId);
        orderFromUser.setPurchaseQuantity(purchaseQuantity);

        return orderFromUser;
    }
}
