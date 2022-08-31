package by.epam.onlinestore.dao.mapper.impl;


import by.epam.onlinestore.bean.ProductCategory;
import by.epam.onlinestore.dao.mapper.ColumnLabel;
import by.epam.onlinestore.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryMapper implements Mapper<ProductCategory> {

    @Override
    public ProductCategory map(ResultSet resultSet) throws SQLException {
        ProductCategory productCategory = new ProductCategory();

        productCategory.setId(resultSet.getLong(ColumnLabel.PRODUCT_CATEGORY_ID));
        productCategory.setProductCategoryName(resultSet.getString(ColumnLabel.PRODUCT_CATEGORY_NAME));

        return productCategory;
    }
}
