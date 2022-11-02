package by.epam.onlinestore.controller.command.impl.transfer;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.User;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import by.epam.onlinestore.service.OrderFromUserService;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.ServiceFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToAddOrderPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToAddOrderPageCommand.class);

    private static final String ADD_ORDER_PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    private static final String USER = "user";
    private static final String TOTAL_COST = "totalCost";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        long userId = user.getId();

        try {
            OrderFromUserService orderFromUserService = ServiceFactory.getInstance().getOrderFromUserService();
            List<OrderFromUser> ordersFromUser =
                    orderFromUserService.retrieveOrdersFromUserByUserWhereProductStatusTrue(userId);

            double totalCost = orderFromUserService.calculateCost(ordersFromUser);
            requestContext.addRequestAttribute(TOTAL_COST, totalCost);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while going to add order page", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(ADD_ORDER_PAGE, CommandResultType.FORWARD);
    }
}
