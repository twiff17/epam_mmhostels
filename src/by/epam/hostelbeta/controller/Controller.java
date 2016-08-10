package by.epam.hostelbeta.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.CommandFactory;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

@WebServlet(urlPatterns = "/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		ICommand command = CommandFactory.getInstance().getCommand(request.getParameter(Parameters.COMMAND));
		
		page = command.setUp(request, response).execute(request, response);
//		if (page == null) {
//			page = ConfigurationManager.getProperty(Parameters.HOME);
//		}
		request.getRequestDispatcher(page).forward(request, response);
	}
}
