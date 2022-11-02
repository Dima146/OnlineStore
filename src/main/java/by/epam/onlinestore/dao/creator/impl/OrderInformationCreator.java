package by.epam.onlinestore.dao.creator.impl;

import by.epam.onlinestore.bean.OrderInformation;
import by.epam.onlinestore.dao.creator.ColumnLabel;
import by.epam.onlinestore.dao.creator.Creator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderInformationCreator implements Creator<OrderInformation> {

    @Override
    public OrderInformation create(ResultSet resultSet) throws SQLException {
        OrderInformation orderInformation = new OrderInformation();

        orderInformation.setId(resultSet.getLong(ColumnLabel.USER_ORDER_ID));
        orderInformation.setDeliveryAddress(resultSet.getString(ColumnLabel.DELIVERY_ADDRESS));
        orderInformation.setOrderDate(resultSet.getDate(ColumnLabel.ORDER_DATE));
        orderInformation.setDeliveryDate(resultSet.getDate(ColumnLabel.DELIVERY_DATE));
        orderInformation.setOrderStatus(resultSet.getString(ColumnLabel.ORDER_STATUS));

        return orderInformation;
    }
}
