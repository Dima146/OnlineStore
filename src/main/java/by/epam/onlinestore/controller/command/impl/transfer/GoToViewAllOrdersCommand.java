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

public class GoToViewAllOrdersCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToViewAllOrdersCommand.class);

    private static final String VIEW_ORDERS_PAGE = "WEB-INF/view/viewOrders.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    private static final String PRODUCT_CATEGORIES = "categories";
    private static final String ORDERS_INFORMATION = "ordersInformation";
    private static final String USERS = "users";
    private static final String PRODUCTS = "products";
    private static final String ORDERS_FORM_USERS = "ordersFromUser";
    private static final String USER_INFORMATION = "userInformation";
    private static final String EXPECTED = "ожидается";


    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        try {
            ProductCategoryService productCategoryService = ServiceFactory.getInstance().getProductCategoryService();
            List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
            requestContext.addRequestAttribute(PRODUCT_CATEGORIES, categories);

            OrderInformationService orderInformationService = ServiceFactory.getInstance().getOrderInformationService();
            List<OrderInformation> ordersInformation = orderInformationService.retrieveOrderInformationByStatus(EXPECTED);
            requestContext.addRequestAttribute(ORDERS_INFORMATION, ordersInformation);

            OrderFromUserService orderFromUserService = ServiceFactory.getInstance().getOrderFromUserService();
            List<OrderFromUser> ordersFromUser = orderFromUserService.retrieveOrdersFromUserFromOrderInformation(ordersInformation);
            requestContext.addRequestAttribute(ORDERS_FORM_USERS, ordersFromUser);

            ProductService productService = ServiceFactory.getInstance().getProductService();
            List<Product> products = productService.retrieveProductsFromOrdersFromUsers(ordersFromUser);
            requestContext.addRequestAttribute(PRODUCTS, products);

            UserService userService = ServiceFactory.getInstance().getUserService();
            List<User> users = userService.retrieveUsersFromOrders(ordersFromUser);
            requestContext.addRequestAttribute(USERS, users);

            UserInformationService userInformationService = ServiceFactory.getInstance().getUserInformationService();
            List<UserInformation> userInformation = userInformationService.retrieveUserInformationFromUsers(users);
            requestContext.addRequestAttribute(USER_INFORMATION, userInformation);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while viewing orders from users");
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(VIEW_ORDERS_PAGE, CommandResultType.FORWARD);
    }
}
