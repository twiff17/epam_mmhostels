package by.epam.hostelbeta.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.command.CommandFactory;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.Parameters;

@WebServlet("/CheckLoginController")
public class CheckLoginController extends HttpServlet {
	private static final String ENCODING = "UTF-8";
	
	private static final long serialVersionUID = 1L;
    public CheckLoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICommand command = CommandFactory.getInstance().getCommand(request.getParameter(Parameters.COMMAND));
		String message = "";
		try {
			message = command.execute(request, response);
		} catch (CommandException e) {
			message = Parameters.LOGIN_CHECK_ERROR;
		}
		response.setCharacterEncoding(ENCODING);
		response.getWriter().println(message);
	}
}
