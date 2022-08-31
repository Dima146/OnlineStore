package by.epam.onlinestore.command;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandFactory;
import by.epam.onlinestore.controller.command.CommandName;
import by.epam.onlinestore.controller.command.impl.AddInBasketCommand;
import by.epam.onlinestore.controller.command.impl.RegisterCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandFactoryTest {

    @Test
    public void testCommand_ShouldReturnTrueIfAddInBasketCommandExists() {

        Command actual = CommandFactory.getInstance().getCommand(CommandName.ADD_IN_BASKET_COMMAND);
        assertEquals(actual.getClass(), AddInBasketCommand.class);
    }

    @Test
    public void testCommand_ShouldReturnTrueIfAddInRegisterCommandExists() {

        Command actual = CommandFactory.getInstance().getCommand(CommandName.REGISTER_COMMAND);
        assertEquals(actual.getClass(), RegisterCommand.class);
    }
}
