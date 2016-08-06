package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.ConfigurationManager;

public class ChangeLocaleCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String locale = request.getParameter("locale");
		HttpSession session = request.getSession();
		session.setAttribute("locale", locale);
		String page = (String) session.getAttribute("page");
		if (page != null) {
			return ConfigurationManager.getProperty("path.page." + page);
		}else{
			return ConfigurationManager.getProperty("path.page.home");
		}
	}
}
