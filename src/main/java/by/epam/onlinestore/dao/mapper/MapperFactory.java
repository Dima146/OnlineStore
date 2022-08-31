package by.epam.onlinestore.dao.mapper;

import by.epam.onlinestore.bean.*;
import by.epam.onlinestore.dao.mapper.impl.*;

public class MapperFactory {

    private final Mapper<User> userMapper = new UserMapper();
    private final Mapper<UserInformation> userInformationMapper = new UserInformationMapper();
    private final Mapper<Role> roleMapper = new RoleMapper();
    private final Mapper<ProductCategory> productCategoryMapper = new ProductCategoryMapper();
    private final Mapper<Product> productMapper = new ProductMapper();
    private final Mapper<OrderInformation> orderInformationMapper = new OrderInformationMapper();
    private final Mapper<OrderFromUser> orderFromUserMapper = new OrderFromUserMapper();
    private final Mapper<BankCard> bankCardMapper = new BankCardMapper();



    public static MapperFactory getInstance() {
        return Holder.INSTANCE;
    }

    private MapperFactory() {
    }

    public Mapper<User> getUserMapper() {
        return userMapper;
    }

    public Mapper<UserInformation> getUserInformationMapper() {
        return userInformationMapper;
    }

    public Mapper<Role> getRoleMapper() {
        return roleMapper;
    }

    public Mapper<ProductCategory> getProductCategoryMapper() {
        return productCategoryMapper;
    }

    public Mapper<Product> getProductMapper() {
        return productMapper;
    }

    public Mapper<OrderInformation> getOrderInformationMapper() {
        return orderInformationMapper;
    }

    public Mapper<OrderFromUser> getOrderFromUserMapper() {
        return orderFromUserMapper;
    }

    public Mapper<BankCard> getBankCardMapper() {
        return bankCardMapper;
    }

    private static class Holder {
        private static final MapperFactory INSTANCE = new MapperFactory();
    }
}
