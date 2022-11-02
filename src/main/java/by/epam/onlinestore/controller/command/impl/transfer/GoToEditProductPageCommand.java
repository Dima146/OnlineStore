package by.epam.onlinestore.controller.command.impl.transfer;

import by.epam.onlinestore.bean.Product;
import by.epam.onlinestore.bean.ProductCategory;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import by.epam.onlinestore.service.ProductCategoryService;
import by.epam.onlinestore.service.ProductService;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.ServiceFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GoToEditProductPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToEditProductPageCommand.class);

    private static final String EDIT_PRODUCT_PAGE = "WEB-INF/view/editProduct.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    private static final String PRODUCT_CATEGORIES = "categories";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT = "product";
    private static final String PRODUCT_CATEGORY = "category";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        try {
            ProductCategoryService productCategoryService = ServiceFactory.getInstance().getProductCategoryService();
            List<ProductCategory> categories = productCategoryService.retrieveProductCategories();
            requestContext.addRequestAttribute(PRODUCT_CATEGORIES, categories);

            long productId = Long.parseLong(requestContext.getRequestParameter(PRODUCT_ID));
            ProductService productService = ServiceFactory.getInstance().getProductService();
            Optional<Product> product = productService.retrieveProductById(productId);
            if (product.isPresent()) {
                requestContext.addRequestAttribute(PRODUCT, product.get());
                Optional<ProductCategory> category =
                        productCategoryService.retrieveCategoryById(product.get().getProductCategoryId());
                if (category.isPresent()) {
                    requestContext.addRequestAttribute(PRODUCT, product.get());
                    requestContext.addRequestAttribute(PRODUCT_CATEGORY, category.get());
                }
            }
        } catch (ServiceException exception) {
            logger.log(Level.ERROR,"Error while going to edit product page", exception);
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(EDIT_PRODUCT_PAGE, CommandResultType.FORWARD);
    }
}
