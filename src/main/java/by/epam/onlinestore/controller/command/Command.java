package by.epam.onlinestore.controller.command;

import by.epam.onlinestore.controller.context.RequestContextHelper;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    /**
     * Executed method by controller when certain command is called
     *
     * @param helper - RequestContextHelper request
     * @param response HttpServletResponse response
     * @return CommandResult of page with redirection type
     */
    CommandResult execute(RequestContextHelper helper, HttpServletResponse response);
}
