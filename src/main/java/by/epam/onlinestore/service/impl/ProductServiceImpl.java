package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.Product;
import by.epam.onlinestore.bean.ProductCategory;

import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.DaoFactory;
import by.epam.onlinestore.dao.ProductCategoryDao;
import by.epam.onlinestore.dao.ProductDao;

import by.epam.onlinestore.service.ProductService;
import by.epam.onlinestore.service.ServiceException;
import by.epam.onlinestore.service.validator.Validator;
import by.epam.onlinestore.service.validator.ValidatorFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    @Override
    public Optional<Product> retrieveProductById(long productId) throws ServiceException {

        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            Optional<Product> product;
            product = productDao.findProductById(productId);

            return product;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve Product by ID", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Product> retrieveProductsByProductCategoryId(long productCategoryId) throws ServiceException {

        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            List<Product> productList;
            productList = productDao.findProductByCategoryId(productCategoryId);

            return productList;

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve Product by product Category ID", exception);
            throw new ServiceException(exception);

        }
    }

    @Override
    public List<Product> retrieveProductsFromOrdersFromUsers(List<OrderFromUser> ordersFromUsers)
            throws ServiceException {

        List<Product> productList = new LinkedList<>();

        for (OrderFromUser orderFromUser : ordersFromUsers) {
            Optional<Product> product = retrieveProductById(orderFromUser.getProductId());
            if (product.isPresent()) {
                if (!productList.contains(product.get())) {
                    productList.add(product.get());
                }
            }
        }

        return productList;
    }

    @Override
    public boolean addNewProduct(String productName, String productPhotoReference, String price,
                                 String categoryName, boolean status, String description) throws ServiceException {

        if (productName == null || productPhotoReference == null || categoryName == null || description == null) {
            return false;
        }

        Validator priceValidator = ValidatorFactory.getInstance().getPriceValidator();
        if (!priceValidator.isValid(price)) {
            return false;
        }

        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            Optional<Product> availableProduct = productDao.findProductByName(productName);
            if (availableProduct.isPresent()) {
                return false;
            }

            long productCategoryId = retrieveAvailableCategoryId(categoryName);
            double doublePrice = Double.parseDouble(price);
            Product product = buildProduct(productCategoryId, productName, description, doublePrice, status, productPhotoReference);
            productDao.saveProduct(product);

            return true;

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to add  new product", exception);
            throw new ServiceException(exception);
        }

    }

    @Override
    public void updateProductInformation(String productId, String productName, String productPhotoReference,
                                         String price, String categoryName, boolean status, String description) throws ServiceException {

        if (productId == null || productName == null || productPhotoReference == null || price == null ||
                categoryName == null || description == null) {
            return;
        }

        Validator priceValidator = ValidatorFactory.getInstance().getPriceValidator();
        if (!priceValidator.isValid(price)) {
            return;
        }

        Validator idValidator = ValidatorFactory.getInstance().getIdValidator();
        if (!idValidator.isValid(productId)) {
            return;
        }

        long categoryId = retrieveAvailableCategoryId(categoryName);
        long longProductId = Long.parseLong(productId);

        try {

            double doublePrice = Double.parseDouble(price);
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            Product product = buildProduct(categoryId, productName, description, doublePrice, status, productPhotoReference);
            productDao.updateProductById(longProductId, product);

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to update Product information", exception);
            throw new ServiceException(exception);
        }

    }

    private Product buildProduct(long categoryId, String name, String description, double price,
                                 boolean status, String photoReference) {

        Product product = new Product();
        product.setProductCategoryId(categoryId);
        product.setProductName(name);
        product.setProductDescription(description);
        product.setProductPrice(price);
        product.setProductStatus(status);
        product.setProductPhotoReference(photoReference);

        return product;
    }

    private long retrieveAvailableCategoryId(String categoryName) throws ServiceException {
        long productCategoryId;

        try {
            ProductCategoryDao productCategoryDao = DaoFactory.getInstance().getProductCategoryDao();
            Optional<ProductCategory> availableProductCategory = productCategoryDao.findProductCategoryByName(categoryName);

            if (availableProductCategory.isPresent()) {
                productCategoryId = availableProductCategory.get().getId();
            } else {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryName(categoryName);
                productCategoryId = productCategoryDao.saveProductCategory(productCategory);
            }

            return productCategoryId;

        } catch (DaoException exception) {
            logger.log(Level.ERROR,"Impossible to retrieve product Category ID", exception);
            throw new ServiceException(exception);
        }

    }
}
