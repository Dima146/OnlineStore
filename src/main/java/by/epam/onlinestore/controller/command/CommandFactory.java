package by.epam.onlinestore.controller.command;

import by.epam.onlinestore.controller.command.impl.*;
import by.epam.onlinestore.controller.command.impl.transfer.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {

    private static final Map<String, Command> commands = new HashMap<>();
    public CommandFactory() {
        commands.put(CommandName.MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandName.ADD_ORDER_PAGE, new GoToAddOrderPageCommand());
        commands.put(CommandName.ADD_PRODUCT_PAGE, new GoToAddProductPageCommand());
        commands.put(CommandName.BASKET_PAGE, new GoToBasketPageCommand());
        commands.put(CommandName.CATALOG_PAGE, new GoToCatalogPageCommand());
        commands.put(CommandName.CONTACTS_PAGE, new GoToContactsPageCommand());
        commands.put(CommandName.DEFAULT_PAGE, new GoToDefaultPageCommand());
        commands.put(CommandName.EDIT_PRODUCT_PAGE, new GoToEditProductPageCommand());
        commands.put(CommandName.LOGIN_PAGE, new GoToLogInPageCommand());
        commands.put(CommandName.MY_ORDERS_PAGE, new GoToMyOrdersPageCommand());
        commands.put(CommandName.PROFILE_PAGE, new GoToProfilePageCommand());
        commands.put(CommandName.REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(CommandName.VIEW_ALL_ORDERS_PAGE, new GoToViewAllOrdersCommand());
        commands.put(CommandName.ADD_IN_BASKET_COMMAND, new AddInBasketCommand());
        commands.put(CommandName.COMPLETE_USER_ORDER_COMMAND, new CompleteUserOrderCommand());
        commands.put(CommandName.CONFIRM_ADDING_PRODUCT_COMMAND, new ConfirmAddingProductCommand());
        commands.put(CommandName.CONFIRM_EDIT_PRODUCT_COMMAND, new ConfirmEditProductCommand());
        commands.put(CommandName.CONFIRM_ORDER_COMMAND, new ConfirmOrderCommand());
        commands.put(CommandName.DELETE_ORDER_FROM_USER_COMMAND, new DeleteOrderFromUserCommand());
        commands.put(CommandName.DELETE_ORDER_INFORMATION_COMMAND, new DeleteOrderInformationCommand());
        commands.put(CommandName.LOG_IN_COMMAND, new LogInCommand());
        commands.put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        commands.put(CommandName.REGISTER_COMMAND, new RegisterCommand());
    }

    public static CommandFactory getInstance() {
        return Holder.INSTANCE;
    }

    public Command getCommand(String commandName) {
        return Optional.ofNullable(commands.get(commandName)).orElse(commands.get(CommandName.DEFAULT_PAGE));

    }

    private static class Holder {
        private static final CommandFactory INSTANCE = new CommandFactory();

    }
}

