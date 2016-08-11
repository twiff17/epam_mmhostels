package by.epam.hostelbeta.command;

import by.epam.hostelbeta.command.impl.ChangeLocaleCommand;
import by.epam.hostelbeta.command.impl.CheckLoginCommand;
import by.epam.hostelbeta.command.impl.GetCabinetCommand;
import by.epam.hostelbeta.command.impl.GetHomeCommand;
import by.epam.hostelbeta.command.impl.GetHostelsCommand;
import by.epam.hostelbeta.command.impl.GetPageCommand;
import by.epam.hostelbeta.command.impl.LoginCommand;
import by.epam.hostelbeta.command.impl.LogoutCommand;
import by.epam.hostelbeta.command.impl.RegistrationCommand;

public enum CommandEnum {
	GET_PAGE(new GetPageCommand()),
	CHANGE_LOCALE(new ChangeLocaleCommand()),
	LOGIN(new LoginCommand()),
	LOGOUT(new LogoutCommand()),
	REGISTRATION(new RegistrationCommand()),
	CHECK_LOGIN(new CheckLoginCommand()),
	GET_HOME(new GetHomeCommand()),
	GET_HOSTELS(new GetHostelsCommand()),
	GET_CABINET(new GetCabinetCommand());
	
	ICommand command;
	CommandEnum(ICommand command){
		this.command = command;
	}
	public ICommand getCurrentCommand(){
		return command;
	}
}
