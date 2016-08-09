package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.service.LoginService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.Parameters;

public class CheckLoginCommand implements ICommand{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String message = "";
		try {
			if(LoginService.checkLogin(request.getParameter(Parameters.LOGIN))){
				message = "Логин занят";
			}else{
				message = "";
			}
		} catch (ServiceException e) {
			message = "Ошибка проверки";
		}
		return message;
	}

}
