package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.ConfigurationManager;

public class LogoutCommand implements ICommand{
	private static final String HOME_PAGE = "path.page.home";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return ConfigurationManager.getProperty(HOME_PAGE);
	}

}
