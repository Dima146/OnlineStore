package by.epam.onlinestore.controller.command.impl;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import by.epam.onlinestore.service.ProductService;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.ServiceFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ConfirmAddingProductCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ConfirmAddingProductCommand.class);

    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    private static final String ADD_PRODUCT_PAGE = "command=addProduct";
    private static final String ERROR = "error";
    private static final String OK = "ok";
    private static final String MESSAGE_PARAMETER = "&message=";

    private static final String PRODUCT_NAME = "product-name";
    private static final String PRODUCT_PHOTO_REFERENCE = "photo";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_CATEGORY = "category";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_AVAILABILITY = "availability";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();
        String message = ERROR;

        Optional<String> productAvailability = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_AVAILABILITY));
        Optional<String> productDescription = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_DESCRIPTION));
        Optional<String> productCategory = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_CATEGORY));
        Optional<String> productPrice = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_PRICE));
        Optional<String> productPhotoReference = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_PHOTO_REFERENCE));
        Optional<String> productName = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_NAME));

        try {
            if (productName.isPresent() && productPhotoReference.isPresent() && productPrice.isPresent() && productCategory.isPresent()
                && productDescription.isPresent()) {

                boolean availability = productAvailability.isPresent();

                ProductService productService = ServiceFactory.getInstance().getProductService();
                boolean addingNewProductResult = productService.addNewProduct(productName.get(), productPhotoReference.get(),
                                productPrice.get(), productCategory.get(), availability, productDescription.get());

                if (addingNewProductResult) {
                    message = OK;
                }
            }
        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while product adding", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(ADD_PRODUCT_PAGE + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
    }
}
