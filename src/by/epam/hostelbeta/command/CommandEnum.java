package by.epam.hostelbeta.command;

import by.epam.hostelbeta.command.impl.GetPageCommand;
import by.epam.hostelbeta.command.impl.LoginCommand;
import by.epam.hostelbeta.command.impl.RegistrationCommand;

public enum CommandEnum {
	GET_PAGE(new GetPageCommand()),
	LOGIN(new LoginCommand()),
	REGISTRATION(new RegistrationCommand());
	
	ICommand command;
	CommandEnum(ICommand command){
		this.command = command;
	}
	public ICommand getCurrentCommand(){
		return command;
	}
}
