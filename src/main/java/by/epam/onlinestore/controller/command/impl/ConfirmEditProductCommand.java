package by.epam.onlinestore.controller.command.impl;

import by.epam.onlinestore.bean.Product;
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

public class ConfirmEditProductCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ConfirmEditProductCommand.class);

    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATALOG_PAGE = "command=catalog";
    private static final String CATEGORY_ID_PARAMETER = "&categoryId=";
    private static final String PRODUCT_NAME = "product-name";
    private static final String PRODUCT_PHOTO_REFERENCE = "photo";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_CATEGORY = "category";
    private static final String PRODUCT_DESCRIPTION = "description";
    private static final String PRODUCT_AVAILABILITY = "availability";
    private static final String PRODUCT_ID = "productId";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        Optional<String> productId = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_ID));
        Optional<String> productAvailability = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_AVAILABILITY));
        Optional<String> productDescription = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_DESCRIPTION));
        Optional<String> productCategory = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_CATEGORY));
        Optional<String> productPrice = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_PRICE));
        Optional<String> productPhotoReference = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_PHOTO_REFERENCE));
        Optional<String> productName = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_NAME));

        try {
            ProductService productService = ServiceFactory.getInstance().getProductService();
            long productCategoryId;

            if (productId.isPresent() && productName.isPresent() && productPhotoReference.isPresent() && productPrice.isPresent() &&
                productCategory.isPresent() && productDescription.isPresent()) {

                boolean availability = productAvailability.isPresent();

                productService.updateProductInformation(productId.get(), productName.get(), productPhotoReference.get(),
                        productPrice.get(), productCategory.get(), availability, productDescription.get());
            }

            Optional<Product> product = productService.retrieveProductById(Long.parseLong(productId.get()));

            if (product.isPresent()) {
                productCategoryId = product.get().getProductCategoryId();
                helper.updateRequest(requestContext);
                return new CommandResult(CATALOG_PAGE + CATEGORY_ID_PARAMETER + productCategoryId, CommandResultType.REDIRECT);
            }

            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);

        } catch (ServiceException exception) {
            logger.log(Level.ERROR, "Error while changing product ", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);


        }
    }
}
