package by.epam.onlinestore.dao.mapper;

import by.epam.onlinestore.bean.*;
import by.epam.onlinestore.dao.mapper.impl.*;

public class RowMapperFactory {

    private final RowMapper<User> userRowMapper = new UserRowMapper();
    private final RowMapper<UserInformation> userInformationRowMapper = new UserInformationRowMapper();
    private final RowMapper<Role> roleRowMapper = new RoleRowMapper();
    private final RowMapper<ProductCategory> productCategoryRowMapper = new ProductCategoryRowMapper();
    private final RowMapper<Product> productRowMapper = new ProductRowMapper();
    private final RowMapper<OrderInformation> orderInformationRowMapper = new OrderInformationRowMapper();
    private final RowMapper<OrderFromUser> orderFromUserRowMapper = new OrderFromUserRowMapper();
    private final RowMapper<BankCard> bankCardRowMapper = new BankCardRowMapper();



    public static RowMapperFactory getInstance() {
        return Holder.INSTANCE;
    }

    private RowMapperFactory() {
    }

    public RowMapper<User> getUserMapper() {
        return userRowMapper;
    }

    public RowMapper<UserInformation> getUserInformationMapper() {
        return userInformationRowMapper;
    }

    public RowMapper<Role> getRoleMapper() {
        return roleRowMapper;
    }

    public RowMapper<ProductCategory> getProductCategoryMapper() {
        return productCategoryRowMapper;
    }

    public RowMapper<Product> getProductMapper() {
        return productRowMapper;
    }

    public RowMapper<OrderInformation> getOrderInformationMapper() {
        return orderInformationRowMapper;
    }

    public RowMapper<OrderFromUser> getOrderFromUserMapper() {
        return orderFromUserRowMapper;
    }

    public RowMapper<BankCard> getBankCardMapper() {
        return bankCardRowMapper;
    }

    private static class Holder {
        private static final RowMapperFactory INSTANCE = new RowMapperFactory();
    }
}
