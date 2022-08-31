package by.epam.onlinestore.controller.command.impl;

import by.epam.onlinestore.bean.Product;
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
import java.util.Optional;

public class AddInBasketCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddInBasketCommand.class);

    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    private static final String PAGE = "command=catalog";

    private static final String ERROR = "error";

    private static final String CATEGORY_ID_PARAMETER = "&categoryId=";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String USER = "user";

    private static final String PRODUCT_ID = "productId";
    private static final String QUANTITY = "quantity";
    private static final String OK = "ok";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        long userId = user.getId();

        try {
            long productId = Long.parseLong(requestContext.getRequestParameter(PRODUCT_ID));
            int quantity = Integer.parseInt(requestContext.getRequestParameter(QUANTITY));
            OrderFromUserService orderFromUserService = ServiceFactory.getInstance().getOrderFromUserService();
            String message = OK;
            boolean addingNewOrderResult = orderFromUserService.addNewOrder(userId, productId, quantity);

            if (!addingNewOrderResult) message = ERROR;

            ProductService productService = ServiceFactory.getInstance().getProductService();
            Optional<Product> product = productService.retrieveProductById(productId);

            long categoryId = 0;

            if (product.isPresent()) {
                categoryId = product.get().getProductCategoryId();
            }

            helper.updateRequest(requestContext);
            return new CommandResult(PAGE + CATEGORY_ID_PARAMETER + categoryId + MESSAGE_PARAMETER + message,
                                        CommandResultType.REDIRECT);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while adding product in basket");
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
    }
}
