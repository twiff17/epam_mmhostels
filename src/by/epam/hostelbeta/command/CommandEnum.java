package by.epam.hostelbeta.command;

import by.epam.hostelbeta.command.impl.AcceptOrderCommand;
import by.epam.hostelbeta.command.impl.AddHostelCommand;
import by.epam.hostelbeta.command.impl.CancelOrderCommand;
import by.epam.hostelbeta.command.impl.ChangeLocaleCommand;
import by.epam.hostelbeta.command.impl.CheckLoginCommand;
import by.epam.hostelbeta.command.impl.DeleteHostelCommand;
import by.epam.hostelbeta.command.impl.EditHostelCommand;
import by.epam.hostelbeta.command.impl.GetCabinetCommand;
import by.epam.hostelbeta.command.impl.GetHomeCommand;
import by.epam.hostelbeta.command.impl.GetHostelAddCommand;
import by.epam.hostelbeta.command.impl.GetHostelEditCommand;
import by.epam.hostelbeta.command.impl.GetHostelsAdminCommand;
import by.epam.hostelbeta.command.impl.GetHostelsCommand;
import by.epam.hostelbeta.command.impl.GetOrdersCommand;
import by.epam.hostelbeta.command.impl.GetPageCommand;
import by.epam.hostelbeta.command.impl.GetRoomsCommand;
import by.epam.hostelbeta.command.impl.LoginCommand;
import by.epam.hostelbeta.command.impl.LogoutCommand;
import by.epam.hostelbeta.command.impl.RegistrationCommand;
import by.epam.hostelbeta.command.impl.RejectOrderCommand;

public enum CommandEnum {
	GET_PAGE(new GetPageCommand()),
	CHANGE_LOCALE(new ChangeLocaleCommand()),
	LOGIN(new LoginCommand()),
	LOGOUT(new LogoutCommand()),
	REGISTRATION(new RegistrationCommand()),
	CHECK_LOGIN(new CheckLoginCommand()),
	GET_HOME(new GetHomeCommand()),
	GET_HOSTELS(new GetHostelsCommand()),
	GET_CABINET(new GetCabinetCommand()),
	GET_ROOMS(new GetRoomsCommand()),
	GET_ORDERS(new GetOrdersCommand()),
	REJECT_ORDER(new RejectOrderCommand()),
	CANCEL_ORDER(new CancelOrderCommand()),
	ACCEPT_ORDER(new AcceptOrderCommand()),
	GET_HOSTELS_ADMIN(new GetHostelsAdminCommand()),
	DELETE_HOSTEL(new DeleteHostelCommand()),
	GET_HOSTEL_ADD(new GetHostelAddCommand()),
	ADD_HOSTEL(new AddHostelCommand()),
	GET_HOSTEL_EDIT(new GetHostelEditCommand()),
	EDIT_HOSTEL(new EditHostelCommand());
	
	ICommand command;
	CommandEnum(ICommand command){
		this.command = command;
	}
	public ICommand getCurrentCommand(){
		return command;
	}
}
