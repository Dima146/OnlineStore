package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.Product;
import by.epam.onlinestore.dao.AbstractSqlExecutor;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.ProductDao;
import by.epam.onlinestore.dao.TableLabel;
import by.epam.onlinestore.dao.mapper.MapperFactory;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl extends AbstractSqlExecutor<Product> implements ProductDao {

    private static final String FIND_PRODUCTS_BY_CATEGORY_ID =
            "SELECT * FROM " + TableLabel.PRODUCT + " WHERE product_category_id_fk=?";

    private static final String FIND_PRODUCT_BY_NAME = "SELECT * FROM " + TableLabel.PRODUCT + " WHERE product_name=?";

    private static final String SAVE_PRODUCT = "INSERT INTO " + TableLabel.PRODUCT +
            " (product_category_id_fk, product_name, product_description, " +
            "product_price, product_status, product_photo_reference, available_quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT = "UPDATE " + TableLabel.PRODUCT + " SET product_category_id_fk=?, " +
            "product_name=?, product_description=?, product_price=?, product_status=?, product_photo_reference=? WHERE product_id=?";

    private static final String FIND_PRODUCT_BY_ID = "SELECT * FROM " + TableLabel.PRODUCT + " WHERE product_id=?";


    public ProductDaoImpl() {
        super(MapperFactory.getInstance().getProductMapper(), TableLabel.PRODUCT);
    }

    @Override
    public List<Product> findProductByCategoryId(long categoryId) throws DaoException {

        return executeSqlQuery(FIND_PRODUCTS_BY_CATEGORY_ID, categoryId);
    }

    @Override
    public Optional<Product> findProductByName(String name) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_PRODUCT_BY_NAME, name);
    }

    @Override
    public void updateProductById(long id, Product product) throws DaoException {

        executeUpdateSqlQuery(UPDATE_PRODUCT, product.getProductCategoryId(), product.getProductName(),
                product.getProductDescription(), product.getProductPrice(), product.isProductStatus(),
                product.getProductPhotoReference(), id);

    }

    @Override
    public Optional<Product> findProductById(long id) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_PRODUCT_BY_ID, id);
    }

    @Override
    public long saveProduct(Product product) throws DaoException {

        return executeInsertSqlQuery(SAVE_PRODUCT, product.getProductCategoryId(), product.getProductName(),
                product.getProductDescription(), product.getProductPrice(), product.isProductStatus(),
                product.getProductPhotoReference(), product.getAvailableQuantity());
    }
}
