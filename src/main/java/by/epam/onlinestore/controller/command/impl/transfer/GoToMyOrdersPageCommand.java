package by.epam.onlinestore.controller.command.impl.transfer;

import by.epam.onlinestore.bean.*;

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

public class GoToMyOrdersPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToMyOrdersPageCommand.class);

    private static final String MY_ORDERS_PAGE = "WEB-INF/view/myOrders.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    private static final String ORDERS_INFORMATION = "ordersInformation";
    private static final String ORDERS_FROM_USER = "ordersFromUser";
    private static final String PRODUCTS = "products";
    private static final String PRODUCT_CATEGORIES = "categories";
    private static final String USER = "user";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(MY_ORDERS_PAGE, CommandResultType.FORWARD);
        }

        try {
            ProductCategoryService productCategoryService = ServiceFactory.getInstance().getProductCategoryService();
            List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
            requestContext.addRequestAttribute(PRODUCT_CATEGORIES, categories);

            long userId = user.getId();
            OrderFromUserService orderFromUserService = ServiceFactory.getInstance().getOrderFromUserService();
            List<OrderFromUser> ordersFromUser = orderFromUserService.retrieveOrdersFromUserByUserId(userId);
            requestContext.addRequestAttribute(ORDERS_FROM_USER, ordersFromUser);

            OrderInformationService orderInformationService = ServiceFactory.getInstance().getOrderInformationService();
            List<OrderInformation> ordersInformation =
                    orderInformationService.retrieveOrderInformationFromOrdersFromUser(ordersFromUser);

            requestContext.addRequestAttribute(ORDERS_INFORMATION, ordersInformation);

            ProductService productService = ServiceFactory.getInstance().getProductService();
            List<Product> products = productService.retrieveProductsFromOrdersFromUsers(ordersFromUser);
            requestContext.addRequestAttribute(PRODUCTS, products);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while going to my orders page ", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(MY_ORDERS_PAGE, CommandResultType.FORWARD);
    }
}
