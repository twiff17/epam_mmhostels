package by.epam.hostelbeta.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.CommandFactory;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.Parameters;

/**
 * Servlet implementation class CheckLoginController
 */
@WebServlet("/CheckLoginController")
public class CheckLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckLoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICommand command = CommandFactory.getInstance().getCommand(request.getParameter(Parameters.COMMAND));
		String message = command.execute(request, response);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(message);
	}

}
