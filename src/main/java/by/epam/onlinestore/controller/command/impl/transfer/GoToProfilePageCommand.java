package by.epam.onlinestore.controller.command.impl.transfer;

import by.epam.onlinestore.bean.ProductCategory;
import by.epam.onlinestore.bean.User;
import by.epam.onlinestore.bean.UserInformation;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import by.epam.onlinestore.service.ProductCategoryService;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.ServiceFactory;
import by.epam.onlinestore.service.UserInformationService;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GoToProfilePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToProfilePageCommand.class);

    private static final String PROFILE_PAGE = "WEB-INF/view/profile.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    private static final String USER = "user";
    private static final String USER_INFORMATION = "userInformation";
    private static final String PRODUCT_CATEGORIES = "categories";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PROFILE_PAGE, CommandResultType.FORWARD);
        }

        try {
            ProductCategoryService productCategoryService = ServiceFactory.getInstance().getProductCategoryService();
            List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
            requestContext.addRequestAttribute(PRODUCT_CATEGORIES, categories);

            long userInformationId = user.getUserInformationId();
            UserInformationService userInformationService = ServiceFactory.getInstance().getUserInformationService();
            Optional<UserInformation> userInformation = userInformationService.retrieveUserInformationById(userInformationId);
            userInformation.ifPresent(information -> requestContext.addRequestAttribute(USER_INFORMATION, information));

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while going to profile page ", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PROFILE_PAGE, CommandResultType.FORWARD);
    }
}
