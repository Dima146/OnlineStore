package by.epam.onlinestore.service;

import by.epam.onlinestore.service.impl.*;

public class ServiceFactory {

    private final UserService userService = new UserServiceImpl();
    private final UserInformationService userInformationService = new UserInformationServiceImpl();
    private final RoleService roleService = new RoleServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();
    private final OrderInformationService orderInformationService = new OrderInformationServiceImpl();
    private final OrderFromUserService orderFromUserService = new OrderFromUserServiceImpl();
    private final BankCardService bankCardService = new BankCardServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserInformationService getUserInformationService() {
        return userInformationService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public ProductCategoryService getProductCategoryService() {
        return productCategoryService;
    }

    public OrderInformationService getOrderInformationService() {
        return orderInformationService;
    }

    public OrderFromUserService getOrderFromUserService() {
        return orderFromUserService;
    }

    public BankCardService getBankCardService() {
        return bankCardService;
    }

    private static class Holder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }
}
