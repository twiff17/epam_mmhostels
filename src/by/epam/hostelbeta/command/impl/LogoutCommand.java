package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class LogoutCommand extends AbstractCommand implements ICommand{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		request.getSession().setAttribute(Parameters.PAGE, Parameters.HOME);
		return ConfigurationManager.getProperty(Parameters.HOME_PATH);
	}

}
