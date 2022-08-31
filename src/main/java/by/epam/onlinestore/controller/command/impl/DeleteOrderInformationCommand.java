package by.epam.onlinestore.controller.command.impl;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import by.epam.onlinestore.service.OrderInformationService;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.ServiceFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;

public class DeleteOrderInformationCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteOrderInformationCommand.class);

    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String VIEW_ORDERS_PAGE = "command=viewOrders";

    private static final String ORDER_INFORMATION_ID = "orderInformationId";
    private static final String CANCELED = "отменен";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        try {
            long orderInformationId = Long.parseLong(requestContext.getRequestParameter(ORDER_INFORMATION_ID));
            OrderInformationService orderInformationService = ServiceFactory.getInstance().getOrderInformationService();
            orderInformationService.updateStatusInOrderInformationById(orderInformationId, CANCELED);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while deleting order information", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }


        helper.updateRequest(requestContext);
        return new CommandResult(VIEW_ORDERS_PAGE, CommandResultType.REDIRECT);
    }
}
