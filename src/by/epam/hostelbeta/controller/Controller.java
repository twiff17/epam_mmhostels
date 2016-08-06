package by.epam.hostelbeta.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.hostelbeta.command.CommandFactory;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.ConfigurationManager;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	static final Logger LOGGER = LogManager.getLogger(Controller.class);
	private static final long serialVersionUID = 1L;
	
	private static final String COMMAND = "command";
	private static final String INDEX_PAGE = "path.page.index";

	public Controller() {
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

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		ICommand command = CommandFactory.getInstance().getCommand(request.getParameter(COMMAND));
		page = command.execute(request, response);
		if (page == null) {
			page = ConfigurationManager.getProperty(INDEX_PAGE);
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
}
