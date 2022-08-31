package by.epam.onlinestore.service;

import by.epam.onlinestore.bean.ProductCategory;
import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    /**
     * Retrieve product categories
     *
     * @return List of product categories
     */
    List<ProductCategory> retrieveProductCategories() throws ServiceException;

    /**
     * Retrieve product category by ID
     *
     * @param productCategoryId - ProductCategory ID
     * @return ProductCategory
     */
    Optional<ProductCategory> retrieveCategoryById(long productCategoryId) throws ServiceException;
}
