package by.epam.onlinestore.controller;

import by.epam.onlinestore.controller.command.Command;
import by.epam.onlinestore.controller.command.CommandFactory;
import by.epam.onlinestore.controller.command.CommandResult;
import by.epam.onlinestore.controller.context.RequestContextHelper;
import by.epam.onlinestore.dao.connectionpool.ConnectionPool;
import by.epam.onlinestore.dao.connectionpool.ConnectionPoolException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/OnlineStore")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    private static final String COMMAND = "command";
    private static final String MAIN_COMMAND = "command=main";
    private static final String PATH = "/OnlineStore?";

    @Override
    public void init() throws ServletException {
        super.init();

        try {
            ConnectionPool.getInstance().initPoolData();

        } catch (ConnectionPoolException exception) {
            logger.log(Level.ERROR, "Servlet has not been init", exception);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        process(request, response);

    }

    @Override
    public void destroy() {

        try {
            ConnectionPool.getInstance().dispose();
            super.destroy();

        } catch (Exception exception) {
            logger.log(Level.ERROR,"Servlet has not been destroyed", exception);
        }

    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String commandName = request.getParameter(COMMAND);

        if (commandName == null || "".equals(commandName)) {
            response.sendRedirect(PATH + MAIN_COMMAND);
        }else {
            Command command = CommandFactory.getInstance().getCommand(commandName);
            RequestContextHelper helper = new RequestContextHelper(request);

            CommandResult result = command.execute(helper, response);
            dispatch(result, request, response);
        }
    }

    private void dispatch(CommandResult result, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        if (result.isRedirect()) {
            response.sendRedirect("http://localhost:8080/OnlineStore-1.0-SNAPSHOT" + PATH + result.getPage());

        } else {
            request.getRequestDispatcher(result.getPage()).forward(request, response);
        }
    }
}