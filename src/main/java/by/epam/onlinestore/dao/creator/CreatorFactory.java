package by.epam.onlinestore.dao.creator;

import by.epam.onlinestore.bean.*;
import by.epam.onlinestore.dao.creator.impl.*;

public class CreatorFactory {

    private final Creator<User> userCreator = new UserCreator();
    private final Creator<UserInformation> userInformationCreator = new UserInformationCreator();
    private final Creator<Role> roleCreator = new RoleCreator();
    private final Creator<ProductCategory> productCategoryCreator = new ProductCategoryCreator();
    private final Creator<Product> productCreator = new ProductCreator();
    private final Creator<OrderInformation> orderInformationCreator = new OrderInformationCreator();
    private final Creator<OrderFromUser> orderFromUserCreator = new OrderFromUserCreator();
    private final Creator<BankCard> bankCardCreator = new BankCardCreator();



    public static CreatorFactory getInstance() {
        return Holder.INSTANCE;
    }

    private CreatorFactory() {
    }

    public Creator<User> getUserMapper() {
        return userCreator;
    }

    public Creator<UserInformation> getUserInformationMapper() {
        return userInformationCreator;
    }

    public Creator<Role> getRoleMapper() {
        return roleCreator;
    }

    public Creator<ProductCategory> getProductCategoryMapper() {
        return productCategoryCreator;
    }

    public Creator<Product> getProductMapper() {
        return productCreator;
    }

    public Creator<OrderInformation> getOrderInformationMapper() {
        return orderInformationCreator;
    }

    public Creator<OrderFromUser> getOrderFromUserMapper() {
        return orderFromUserCreator;
    }

    public Creator<BankCard> getBankCardMapper() {
        return bankCardCreator;
    }

    private static class Holder {
        private static final CreatorFactory INSTANCE = new CreatorFactory();
    }
}
