package by.epam.onlinestore.dao.mapper.impl;

import by.epam.onlinestore.bean.Product;
import by.epam.onlinestore.dao.mapper.ColumnLabel;
import by.epam.onlinestore.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements Mapper<Product> {

    @Override
    public Product map(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getLong(ColumnLabel.PRODUCT_ID));
        product.setProductName(resultSet.getString(ColumnLabel.PRODUCT_NAME));
        product.setProductDescription(resultSet.getString(ColumnLabel.PRODUCT_DESCRIPTION));
        product.setProductPrice(resultSet.getDouble(ColumnLabel.PRODUCT_PRICE));
        product.setProductStatus(resultSet.getBoolean(ColumnLabel.PRODUCT_STATUS));
        product.setProductPhotoReference(resultSet.getString(ColumnLabel.PRODUCT_PHOTO_REFERENCE));
        product.setAvailableQuantity(resultSet.getLong(ColumnLabel.PRODUCT_AVAILABLE_QUANTITY));
        product.setProductCategoryId(resultSet.getLong(ColumnLabel.PRODUCT_CATEGORY_ID_FK));

        return product;
    }
}
