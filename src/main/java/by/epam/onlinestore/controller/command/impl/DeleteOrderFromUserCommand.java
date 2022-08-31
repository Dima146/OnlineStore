package by.epam.onlinestore.controller.command.impl;

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

public class DeleteOrderFromUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteOrderFromUserCommand.class);

    private static final String BASKET_PAGE = "command=basket";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String ORDER_FROM_USER_ID = "orderFromUserId";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        try {
            long orderFromUserId = Long.parseLong(requestContext.getRequestParameter(ORDER_FROM_USER_ID));
            OrderFromUserService orderFromUserService = ServiceFactory.getInstance().getOrderFromUserService();
            orderFromUserService.removeOrderFromUserById(orderFromUserId);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while deleting order from user ", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(BASKET_PAGE, CommandResultType.REDIRECT);
    }
}
