package by.epam.onlinestore.dao.mapper.impl;

import by.epam.onlinestore.bean.OrderFromUser;
import by.epam.onlinestore.dao.mapper.ColumnLabel;
import by.epam.onlinestore.dao.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderFromUserMapper implements Mapper<OrderFromUser> {

    @Override
    public OrderFromUser map(ResultSet resultSet) throws SQLException {
        OrderFromUser orderFromUser = new OrderFromUser();

        orderFromUser.setId(resultSet.getLong(ColumnLabel.ORDER_FROM_USER_ID));
        orderFromUser.setPurchaseQuantity(resultSet.getInt(ColumnLabel.PURCHASE_QUANTITY));
        orderFromUser.setUserId(resultSet.getLong(ColumnLabel.USER_ID_FK));
        orderFromUser.setProductId(resultSet.getLong(ColumnLabel.PRODUCT_ID_FK));
        orderFromUser.setOrderInformationId(resultSet.getLong(ColumnLabel.ORDER_INFORMATION_ID_FK));

        return orderFromUser;
    }
}
