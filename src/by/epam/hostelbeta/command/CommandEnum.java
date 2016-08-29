package by.epam.hostelbeta.command;

import by.epam.hostelbeta.command.impl.common.ChangeLocaleCommand;
import by.epam.hostelbeta.command.impl.common.GetCabinetCommand;
import by.epam.hostelbeta.command.impl.common.GetHomeCommand;
import by.epam.hostelbeta.command.impl.common.GetPageCommand;
import by.epam.hostelbeta.command.impl.hostel.AddHostelCommand;
import by.epam.hostelbeta.command.impl.hostel.DeleteHostelCommand;
import by.epam.hostelbeta.command.impl.hostel.EditHostelCommand;
import by.epam.hostelbeta.command.impl.hostel.GetHostelAddCommand;
import by.epam.hostelbeta.command.impl.hostel.GetHostelEditCommand;
import by.epam.hostelbeta.command.impl.hostel.GetHostelsAdminCommand;
import by.epam.hostelbeta.command.impl.hostel.GetHostelsCommand;
import by.epam.hostelbeta.command.impl.hostel.SearchByDateCommand;
import by.epam.hostelbeta.command.impl.hostel.SearchByPriceCommand;
import by.epam.hostelbeta.command.impl.order.AcceptOrderCommand;
import by.epam.hostelbeta.command.impl.order.BookRoomCommand;
import by.epam.hostelbeta.command.impl.order.CancelOrderCommand;
import by.epam.hostelbeta.command.impl.order.GetOrdersCommand;
import by.epam.hostelbeta.command.impl.order.RejectOrderCommand;
import by.epam.hostelbeta.command.impl.room.AddRoomCommand;
import by.epam.hostelbeta.command.impl.room.DeleteRoomCommand;
import by.epam.hostelbeta.command.impl.room.EditRoomCommand;
import by.epam.hostelbeta.command.impl.room.GetFreeRoomsCommand;
import by.epam.hostelbeta.command.impl.room.GetRoomAddCommand;
import by.epam.hostelbeta.command.impl.room.GetRoomEditCommand;
import by.epam.hostelbeta.command.impl.room.GetRoomsAdminCommand;
import by.epam.hostelbeta.command.impl.room.GetRoomsCommand;
import by.epam.hostelbeta.command.impl.user.AddDiscountCommand;
import by.epam.hostelbeta.command.impl.user.BanUserCommand;
import by.epam.hostelbeta.command.impl.user.CheckLoginCommand;
import by.epam.hostelbeta.command.impl.user.GetUsersCommand;
import by.epam.hostelbeta.command.impl.user.LoginCommand;
import by.epam.hostelbeta.command.impl.user.LogoutCommand;
import by.epam.hostelbeta.command.impl.user.RegistrationCommand;
import by.epam.hostelbeta.command.impl.user.UnbanUserCommand;

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
	EDIT_HOSTEL(new EditHostelCommand()),
	GET_ROOMS_ADMIN(new GetRoomsAdminCommand()),
	DELETE_ROOM(new DeleteRoomCommand()),
	GET_ROOM_ADD(new GetRoomAddCommand()),
	ADD_ROOM(new AddRoomCommand()),
	GET_ROOM_EDIT(new GetRoomEditCommand()),
	EDIT_ROOM(new EditRoomCommand()),
	GET_USERS(new GetUsersCommand()),
	BAN_USER(new BanUserCommand()),
	UNBAN_USER(new UnbanUserCommand()),
	ADD_DISCOUNT(new AddDiscountCommand()),
	BOOK_ROOM(new BookRoomCommand()),
	SEARCH_BY_DATE(new SearchByDateCommand()),
	SEARCH_BY_PRICE(new SearchByPriceCommand()),
	GET_FREE_ROOMS(new GetFreeRoomsCommand());
	
	ICommand command;
	CommandEnum(ICommand command){
		this.command = command;
	}
	public ICommand getCurrentCommand(){
		return command;
	}
}
