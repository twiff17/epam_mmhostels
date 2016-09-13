package by.epam.hostelbeta.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

/**
 * The Class AjaxController. Handles all AJAX requests. Creates a command and
 * call its method - execute
 */
@SuppressWarnings("serial")
@WebServlet("/AjaxController")
@MultipartConfig
public class AjaxController extends HttpServlet {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(AjaxController.class);

	/**
	 * Instantiates a new ajax controller.
	 */
	public AjaxController() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Process request.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
