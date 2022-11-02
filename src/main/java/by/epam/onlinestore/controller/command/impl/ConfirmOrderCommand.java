package by.epam.onlinestore.controller.command.impl;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.User;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import by.epam.onlinestore.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ConfirmOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ConfirmOrderCommand.class);

    private static final String ADD_ORDER_PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String MY_ORDERS_PAGE = "command=myOrders";

    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String USER = "user";

    private static final String TOTAL_COST = "totalCost";
    private static final String DELIVERY_ADDRESS = "address";
    private static final String DELIVERY_DATE = "delivery-date";
    private static final String CARD_HOLDER_NAME = "cardholder-name";
    private static final String CVV = "cvv";
    private static final String CARD_NUMBER = "card-number";
    private static final String EXPIRATION_MONTH = "month";
    private static final String EXPIRATION_YEAR = "year";


    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        Optional<String> expirationYear = Optional.ofNullable(requestContext.getRequestParameter(EXPIRATION_YEAR));
        Optional<String> expirationMonth = Optional.ofNullable(requestContext.getRequestParameter(EXPIRATION_MONTH));
        Optional<String> cardNumber = Optional.ofNullable(requestContext.getRequestParameter(CARD_NUMBER));
        Optional<String> cvv = Optional.ofNullable(requestContext.getRequestParameter(CVV));
        Optional<String> cardHolderName = Optional.ofNullable(requestContext.getRequestParameter(CARD_HOLDER_NAME));
        Optional<String> deliveryDate = Optional.ofNullable(requestContext.getRequestParameter(DELIVERY_DATE));
        Optional<String> deliveryAddress = Optional.ofNullable(requestContext.getRequestParameter(DELIVERY_ADDRESS));

        try {
            User user = (User) requestContext.getSessionAttribute(USER);
            long userId = user.getId();
            OrderFromUserService orderFromUser = ServiceFactory.getInstance().getOrderFromUserService();
            List<OrderFromUser> ordersFromUser = orderFromUser.retrieveOrdersFromUserByUserWhereProductStatusTrue(userId);
            double totalPrice = orderFromUser.calculateCost(ordersFromUser);

            if (deliveryAddress.isPresent() && deliveryDate.isPresent() && cardHolderName.isPresent() && cvv.isPresent() &&
                cardNumber.isPresent() && expirationMonth.isPresent() && expirationYear.isPresent()) {

                OrderInformationService orderInformationService = ServiceFactory.getInstance().getOrderInformationService();
                boolean addingOrderResult = orderInformationService.addNewOrderInformation(ordersFromUser, deliveryAddress.get(),
                        deliveryDate.get(), cardHolderName.get(), cvv.get(), cardNumber.get(),
                        expirationMonth.get(), expirationYear.get(), totalPrice);

                if (addingOrderResult) {
                    helper.updateRequest(requestContext);
                    return new CommandResult(MY_ORDERS_PAGE, CommandResultType.REDIRECT);
                }
            }

            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);
            requestContext.addRequestAttribute(ERROR_MESSAGE, true);
            helper.updateRequest(requestContext);
            return new CommandResult(ADD_ORDER_PAGE, CommandResultType.FORWARD);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while confirming order", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
    }
}
