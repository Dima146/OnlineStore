package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.OrderInformation;

import by.epam.onlinestore.dao.*;
import by.epam.onlinestore.service.BankCardService;
import by.epam.onlinestore.service.ServiceException;

import by.epam.onlinestore.service.validator.Validator;
import by.epam.onlinestore.service.validator.ValidatorFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderInformationServiceImpl implements by.epam.onlinestore.service.OrderInformationService {

    private static final Logger logger = LogManager.getLogger(OrderInformationServiceImpl.class);

    @Override
    public Optional<OrderInformation> retrieveOrderInformationById(long orderInformationId) throws ServiceException {

        try {
            OrderInformationDao orderInformationDao = DaoFactory.getInstance().getOrderInformationDao();
            Optional<OrderInformation> orderInformation;
            orderInformation = orderInformationDao.findOrderInformationById(orderInformationId);

            return orderInformation;

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to retrieve order information by ID", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<OrderInformation> retrieveOrderInformationByStatus(String orderStatus) throws ServiceException {
        List<OrderInformation> orderInformation;

        try {
            OrderInformationDao orderInformationDao = DaoFactory.getInstance().getOrderInformationDao();
            orderInformation = orderInformationDao.findOrderInformationByStatus(orderStatus);

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to retrieve order information by status", exception);
            throw new ServiceException(exception);
        }
        return orderInformation;
    }

    @Override
    public boolean updateStatusInOrderInformationById(long orderInformationId, String status) throws ServiceException {

        try {
            OrderInformationDao orderInformationDao = DaoFactory.getInstance().getOrderInformationDao();
            Optional<OrderInformation> orderInformation = orderInformationDao.findOrderInformationById(orderInformationId);
            if (orderInformation.isEmpty()) {
                return false;
            }

            orderInformationDao.updateStatusById(orderInformationId, status);

            return true;

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to update order status by ID", exception);
            throw new ServiceException(exception);
        }
    }
    @Override
    public List<OrderInformation> retrieveOrderInformationFromOrdersFromUser(List<OrderFromUser> ordersFromUser)
            throws ServiceException {

        List<OrderInformation> ordersInformation = new LinkedList<>();

        try {
            for (OrderFromUser orderFromUser : ordersFromUser) {
                Optional<OrderInformation> orderInformation = retrieveOrderInformationById(orderFromUser.getOrderInformationId());
                if (orderInformation.isPresent()) {
                    boolean checkOrderFromUserPresence = false;
                    for (OrderInformation information : ordersInformation) {
                        if (information.getId() == orderInformation.get().getId()) {
                            checkOrderFromUserPresence = true;
                        }
                    }
                    if (!checkOrderFromUserPresence) {
                        ordersInformation.add(orderInformation.get());
                    }
                }
            }

            return ordersInformation;

        } catch (ServiceException exception) {
            logger.log(Level.ERROR,"Impossible to retrieve order information from orders from user", exception);
            throw new ServiceException(exception);
        }

    }

    @Override
    public boolean addNewOrderInformation(List<OrderFromUser> ordersFromUsers, String deliveryAddress,
                                          String deliveryDate, String cardholderName, String cvv, String cardNumber,
                                          String month, String year, double price) throws ServiceException {

        try {
            if (deliveryAddress == null || deliveryDate == null || ordersFromUsers.size() < 1 || cardholderName == null ||
                    cvv == null || cardNumber == null || month == null || year == null) {
                return false;
            }

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDate);
            Date currentDate = new Date();
            if (!isDateValid(date, currentDate)) {
                return false;
            }

            Validator cardNumberValidator = ValidatorFactory.getInstance().getCardNumberValidator();
            Validator cvvValidator = ValidatorFactory.getInstance().getCvvValidator();
            Validator monthValidator = ValidatorFactory.getInstance().getMonthValidator();
            Validator yearValidator = ValidatorFactory.getInstance().getYearValidator();

            if (!(cardNumberValidator.isValid(cardNumber) && cvvValidator.isValid(cvv)
                    && monthValidator.isValid(month) && yearValidator.isValid(year))) {
                return false;
            }

            long longCardNumber = Long.parseLong(cardNumber);
            int intCvv = Integer.parseInt(cvv);

            int intMonth = Integer.parseInt(month);
            int intYear = Integer.parseInt(year);
            if (!isCardDateValid(intMonth, intYear)) {
                return false;
            }

            BankCardService bankCardService = new BankCardServiceImpl();
            if (!bankCardService.pay(longCardNumber, intMonth, intYear, intCvv, price)) {
                return false;
            }

            OrderInformation orderInformation = buildOrderInformation(deliveryAddress, date, currentDate, "ожидается");
            OrderInformationDao orderInformationDao = DaoFactory.getInstance().getOrderInformationDao();
            long orderInformationId = orderInformationDao.saveOrderInformation(orderInformation);

            OrderFromUserDao orderFromUserDao = DaoFactory.getInstance().getOrderFromUserDao();
            for (OrderFromUser orderFromUser : ordersFromUsers) {
                orderFromUserDao.updateOrderInformation(orderFromUser.getId(), orderInformationId);
            }

            return true;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to add new order information", exception);
            throw new ServiceException(exception);
        } catch (ParseException exception) {
            logger.log(Level.ERROR, "Error while parsing Date in attempt to add new order information", exception);
            throw new ServiceException(exception);
        }
    }

    private OrderInformation buildOrderInformation(String address, Date deliveryDate, Date orderDate, String orderStatus) {

        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setDeliveryAddress(address);
        orderInformation.setOrderDate(orderDate);
        orderInformation.setDeliveryDate(deliveryDate);
        orderInformation.setOrderStatus(orderStatus);

        return orderInformation;
    }

    private boolean isDateValid(Date deliveryDate, Date currentDate) {
        return deliveryDate.compareTo(currentDate) > 0;
    }

    private boolean isCardDateValid(int month, int year) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int currentMonth = cal.get(Calendar.MONTH);
        int currentYear = cal.get(Calendar.YEAR);

        if (year < currentYear) {
            return false;
        }
        if (year == currentYear) {
            if (month <= currentMonth) {
                return false;
            }
        }
        return true;
    }
}
