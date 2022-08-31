package by.epam.onlinestore.service.impl;

import by.epam.onlinestore.bean.ProductCategory;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.DaoFactory;

import by.epam.onlinestore.dao.ProductCategoryDao;
import by.epam.onlinestore.service.ProductCategoryService;
import by.epam.onlinestore.service.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    private static final Logger logger = LogManager.getLogger(ProductCategoryServiceImpl.class);

    @Override
    public List<ProductCategory> retrieveProductCategories() throws ServiceException {

        List<ProductCategory> productCategoryList;
        try {
            ProductCategoryDao productCategoryDao = DaoFactory.getInstance().getProductCategoryDao();
            productCategoryList = productCategoryDao.findAllProductCategory();

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve List of product categories");
            throw new ServiceException(exception);
        }

        return productCategoryList;
    }

    @Override
    public Optional<ProductCategory> retrieveCategoryById(long productCategoryId) throws ServiceException {

        Optional<ProductCategory> productCategory;
        try {
            ProductCategoryDao productCategoryDao = DaoFactory.getInstance().getProductCategoryDao();
            productCategory = productCategoryDao.findProductCategoryById(productCategoryId);

        } catch (DaoException exception) {
            logger.log(Level.ERROR, "Impossible to retrieve product category by ID");
            throw new ServiceException(exception);
        }

        return productCategory;
    }
}



