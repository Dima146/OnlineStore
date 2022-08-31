package by.epam.onlinestore.service;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.bean.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    /**
     * Retrieve Product by ID
     *
     * @param productId - Product ID
     * @return Product
     */
    Optional<Product> retrieveProductById(long productId) throws ServiceException;

    /**
     * Retrieve products by ProductCategory ID
     *
     * @param productCategoryId - ProductCategory ID
     * @return List of products
     */
    List<Product> retrieveProductsByProductCategoryId(long productCategoryId) throws ServiceException;

    /**
     * Retrieve products from OrdersFromUsers
     *
     * @param ordersFromUsers - List of ordersFromUsers
     * @return List of products
     */
    List<Product> retrieveProductsFromOrdersFromUsers(List<OrderFromUser> ordersFromUsers) throws ServiceException;

    /**
     * Add new product
     *
     * @param productName           - Product name
     * @param productPhotoReference - Product photo reference
     * @param price                 - product price
     * @param categoryName          - Product category name
     * @param status                - product status
     * @param description           - product description
     * @return result of adding
     */
    boolean addNewProduct(String productName, String productPhotoReference, String price, String categoryName,
                          boolean status, String description) throws ServiceException;

    /**
     * Update Product Information
     *
     * @param productName           - Product name
     * @param productPhotoReference - Product photo reference
     * @param price                 - product price
     * @param categoryName          - Product category name
     * @param status                - product status
     * @param description           - product description
     */
    void updateProductInformation(String productId, String productName, String productPhotoReference,
                                  String price, String categoryName, boolean status, String description)
                                    throws ServiceException;

}

