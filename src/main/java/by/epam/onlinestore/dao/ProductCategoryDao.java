package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.ProductCategory;
import java.util.List;
import java.util.Optional;

public interface ProductCategoryDao {

    /**
     * Receive ProductCategory from Database by id
     *
     * @param id - ProductCategory id
     * @return  ProductCategory
     */
    Optional<ProductCategory> findProductCategoryById(long id) throws DaoException;

    /**
     * Save ProductCategory into Database
     *
     * @param productCategory to save
     */
    long saveProductCategory(ProductCategory productCategory) throws DaoException;

    /**
     * Receive ProductCategory from Database by name
     *
     * @param name - ProductCategory name
     * @return ProductCategory
     */
    Optional<ProductCategory> findProductCategoryByName(String name) throws DaoException;

    /**
     * Receive all ProductCategory from Database
     *
     * @return List of ProductCategory
     */

    List<ProductCategory> findAllProductCategory() throws DaoException;

}