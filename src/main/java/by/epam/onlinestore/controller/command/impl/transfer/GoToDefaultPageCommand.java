package by.epam.onlinestore.controller.command.impl.transfer;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.command.CommandResultType;
import by.epam.onlinestore.controller.context.RequestContext;
import by.epam.onlinestore.controller.context.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public class GoToDefaultPageCommand implements Command {

    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createRequestContext();

        helper.updateRequest(requestContext);
        return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
    }
}
