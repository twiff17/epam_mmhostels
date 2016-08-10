package by.epam.hostelbeta.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.service.PageService;
import by.epam.hostelbeta.service.RegistrationService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class RegistrationCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = null;
		User user = new User();
		user.setLogin(request.getParameter(Parameters.LOGIN));
		user.setPassword(request.getParameter(Parameters.PASSWORD));
		user.setEmail(request.getParameter(Parameters.EMAIL));
		user.setFullname(request.getParameter(Parameters.FULLNAME));
		user.setPassport(request.getParameter(Parameters.PASSPORT));
		user.setPhone(request.getParameter(Parameters.PHONE));
		try {
			if (RegistrationService.signUp(user)) {
				HttpSession session = request.getSession();
				session.setAttribute(Parameters.LOGIN, user.getLogin());
				session.setAttribute(Parameters.ROLE, Parameters.ROLE_CLIENT);
				session.setAttribute(Parameters.PAGE, Parameters.HOME);
				HashMap<String, Object> attributes = PageService.getPageAttrubutes(Parameters.HOME, request.getParameterMap());
				if(attributes != null){
					for (Map.Entry<String, Object> entry : attributes.entrySet()) {
				        request.setAttribute(entry.getKey(), entry.getValue());          
				    }
				}
				page = ConfigurationManager.getProperty(Parameters.HOME_PATH);
			} else {
				LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
				request.setAttribute(Parameters.ERROR_REGISTRATION_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.LOGIN_EXISTS_MESSAGE));
				page = ConfigurationManager.getProperty(Parameters.REGISTRATION_PATH);
			}
		} catch (ServiceException e) {
			page = ConfigurationManager.getProperty(Parameters.ERROR_PATH);
		}
		return page;
	}
}
