package by.epam.onlinestore.controller.command.impl.transfer;

import by.epam.onlinestore.bean.ProductCategory;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import by.epam.onlinestore.service.ProductCategoryService;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.ServiceFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToContactsPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToContactsPageCommand.class);

    private static final String CONTACT_PAGE = "WEB-INF/view/contacts.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PRODUCT_CATEGORIES = "categories";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        try {
            ProductCategoryService productCategoryService = ServiceFactory.getInstance().getProductCategoryService();
            List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
            requestContext.addRequestAttribute(PRODUCT_CATEGORIES, categories);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR,"Error while going to contacts page", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(CONTACT_PAGE, CommandResultType.FORWARD);
    }
}
