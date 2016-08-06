package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.entity.User;
import by.epam.hostelbeta.service.RegistrationService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;

public class RegistrationCommand implements ICommand {
	private static final String PARAM_LOGIN = "login"; // для повышения читабельности
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_FULLNAME = "fullname";
	private static final String PARAM_PASSPORT = "passport";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PHONE = "phone";
	private static final String HOME_PAGE = "path.page.home";
	private static final String REGISTRATION_PAGE = "path.page.registration";
	private static final String ERROR_PAGE = "path.page.error";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		User user = new User();
		user.setLogin(request.getParameter(PARAM_LOGIN));
		user.setPassword(request.getParameter(PARAM_PASSWORD));
		user.setEmail(request.getParameter(PARAM_EMAIL));
		user.setFullname(request.getParameter(PARAM_FULLNAME));
		user.setPassport(request.getParameter(PARAM_PASSPORT));
		user.setPhone(request.getParameter(PARAM_PHONE));
		try {
			if(RegistrationService.signUp(user)){
				HttpSession session = request.getSession();
				session.setAttribute("login", user.getLogin());
				session.setAttribute("role", "client");
				session.setAttribute("page", "home");
				page = ConfigurationManager.getProperty(HOME_PAGE);
			}else{
				request.setAttribute("errorRegistrationMessage", "Login exists");
				page = ConfigurationManager.getProperty(REGISTRATION_PAGE);
			}
		} catch (ServiceException e) {
			page = ConfigurationManager.getProperty(ERROR_PAGE);
		}
		return page;
	}
}
