package by.epam.hostelbeta.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.command.CommandFactory;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
	static final Logger LOGGER = LogManager.getLogger(AjaxController.class);
	
	private static final long serialVersionUID = 1L;

	public AjaxController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			ICommand command = CommandFactory.getInstance().getCommand(request.getParameter(Parameters.COMMAND));
			message = command.execute(request, response);
			response.getWriter().println(message);
		} catch (CommandException e) {
			LOGGER.error(e);
			LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
			response.getWriter().println(locManager.getResourceBundle().getString(Parameters.OPERATION_ERROR));
		}
	}
}
