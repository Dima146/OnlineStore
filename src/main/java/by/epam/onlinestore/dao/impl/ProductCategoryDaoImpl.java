package by.epam.onlinestore.dao.impl;

import by.epam.onlinestore.bean.ProductCategory;
import by.epam.onlinestore.dao.AbstractSqlExecutor;
import by.epam.onlinestore.dao.DaoException;
import by.epam.onlinestore.dao.ProductCategoryDao;
import by.epam.onlinestore.dao.TableLabel;
import by.epam.onlinestore.dao.creator.CreatorFactory;

import java.util.List;
import java.util.Optional;

public class ProductCategoryDaoImpl extends AbstractSqlExecutor<ProductCategory> implements ProductCategoryDao {

    private static final String SAVE_PRODUCT_CATEGORY =
            "INSERT INTO " + TableLabel.PRODUCT_CATEGORY + " (product_category_name) VALUES (?)";

    private static final String FIND_PRODUCT_CATEGORY_BY_NAME = "SELECT * FROM " + TableLabel.PRODUCT_CATEGORY + " WHERE product_category_name=?";

    private static final String FIND_PRODUCT_CATEGORY_BY_ID =
            "SELECT * FROM " + TableLabel.PRODUCT_CATEGORY + " WHERE product_category_id=?";

    private static final String FIND_ALL_PRODUCT_CATEGORY = "SELECT * FROM " + TableLabel.PRODUCT_CATEGORY;


    public ProductCategoryDaoImpl() {
        super(CreatorFactory.getInstance().getProductCategoryMapper(), TableLabel.PRODUCT_CATEGORY);
    }

    @Override
    public Optional<ProductCategory> findProductCategoryById(long id) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_PRODUCT_CATEGORY_BY_ID, id);
    }

    @Override
    public long saveProductCategory(ProductCategory productCategory) throws DaoException {

        return executeInsertSqlQuery(SAVE_PRODUCT_CATEGORY, productCategory.getProductCategoryName());
    }

    @Override
    public Optional<ProductCategory> findProductCategoryByName(String name) throws DaoException {

        return executeSqlQueryForSingleResult(FIND_PRODUCT_CATEGORY_BY_NAME, name);
    }

    @Override
    public List<ProductCategory> findAllProductCategory() throws DaoException {
        return executeSqlQuery(FIND_ALL_PRODUCT_CATEGORY);
    }

}
