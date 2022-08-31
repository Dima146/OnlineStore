package by.epam.onlinestore.dao;

import by.epam.onlinestore.bean.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    /**
     * Receive Product from Database by id
     *
     * @param id - Product id
     * @return  Product
     */
    Optional<Product> findProductById(long id) throws DaoException;

    /**
     * Save Product into Database
     *
     * @param product to save
     */
    long saveProduct(Product product) throws DaoException;

    /**
     * Receive Product from Database by name
     *
     * @param name - Product name
     * @return Product
     */
    Optional<Product> findProductByName(String name) throws DaoException;

    /**
     * Receive Product from Database by category id
     *
     * @param categoryId - CategoryProduct id
     * @return List of Products
     */
    List<Product> findProductByCategoryId(long categoryId) throws DaoException;

    /**
     * Update Product in Database by id
     *
     * @param id      - Product id to update
     * @param product - Product to update
     */
    void updateProductById(long id, Product product) throws DaoException;

}
