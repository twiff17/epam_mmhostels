package by.epam.hostelbeta.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.command.CommandFactory;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.command.impl.hostel.AddHostelCommand;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class Controller. Handles all request from client
 */
@SuppressWarnings("serial")
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(Controller.class);

	/** The Constant ERROR_PATH. */
	private static final String ERROR_PATH = "path.page.error";

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
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
	 * Process request. Creates a command and call its method - execute
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
		String page = null;
		ICommand command;
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				command = new AddHostelCommand();
			} else {
				command = CommandFactory.getInstance().getCommand(request.getParameter(Parameters.COMMAND));
			}
			page = command.execute(request, response);
			command.fillCountryList(request);
		} catch (CommandException e) {
			LOGGER.error(e);
			request.setAttribute(Parameters.ERROR_STACKTRACE, e);
			page = ERROR_PATH;
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
}
