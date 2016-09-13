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
import by.epam.hostelbeta.command.impl.user.AddDiscountCommand;
import by.epam.hostelbeta.command.impl.user.BanUserCommand;
import by.epam.hostelbeta.command.impl.user.CheckLoginCommand;
import by.epam.hostelbeta.command.impl.user.GetUsersCommand;
import by.epam.hostelbeta.command.impl.user.LoginCommand;
import by.epam.hostelbeta.command.impl.user.LogoutCommand;
import by.epam.hostelbeta.command.impl.user.RegistrationCommand;
import by.epam.hostelbeta.command.impl.user.UnbanUserCommand;

/**
 * The Enum CommandEnum. Enum of all commands
 */
public enum CommandEnum {

	/** The get page. */
	GET_PAGE(new GetPageCommand()),

	/** The change locale. */
	CHANGE_LOCALE(new ChangeLocaleCommand()),

	/** The login. */
	LOGIN(new LoginCommand()),

	/** The logout. */
	LOGOUT(new LogoutCommand()),

	/** The registration. */
	REGISTRATION(new RegistrationCommand()),

	/** The check login. */
	CHECK_LOGIN(new CheckLoginCommand()),

	/** The get home. */
	GET_HOME(new GetHomeCommand()),

	/** The get hostels. */
	GET_HOSTELS(new GetHostelsCommand()),

	/** The get cabinet. */
	GET_CABINET(new GetCabinetCommand()),

	/** The get orders. */
	GET_ORDERS(new GetOrdersCommand()),

	/** The reject order. */
	REJECT_ORDER(new RejectOrderCommand()),

	/** The cancel order. */
	CANCEL_ORDER(new CancelOrderCommand()),

	/** The accept order. */
	ACCEPT_ORDER(new AcceptOrderCommand()),

	/** The get hostels admin. */
	GET_HOSTELS_ADMIN(new GetHostelsAdminCommand()),

	/** The delete hostel. */
	DELETE_HOSTEL(new DeleteHostelCommand()),

	/** The get hostel add. */
	GET_HOSTEL_ADD(new GetHostelAddCommand()),

	/** The add hostel. */
	ADD_HOSTEL(new AddHostelCommand()),

	/** The get hostel edit. */
	GET_HOSTEL_EDIT(new GetHostelEditCommand()),

	/** The edit hostel. */
	EDIT_HOSTEL(new EditHostelCommand()),

	/** The get rooms admin. */
	GET_ROOMS_ADMIN(new GetRoomsAdminCommand()),

	/** The delete room. */
	DELETE_ROOM(new DeleteRoomCommand()),

	/** The get room add. */
	GET_ROOM_ADD(new GetRoomAddCommand()),

	/** The add room. */
	ADD_ROOM(new AddRoomCommand()),

	/** The get room edit. */
	GET_ROOM_EDIT(new GetRoomEditCommand()),

	/** The edit room. */
	EDIT_ROOM(new EditRoomCommand()),

	/** The get users. */
	GET_USERS(new GetUsersCommand()),

	/** The ban user. */
	BAN_USER(new BanUserCommand()),

	/** The unban user. */
	UNBAN_USER(new UnbanUserCommand()),

	/** The add discount. */
	ADD_DISCOUNT(new AddDiscountCommand()),

	/** The book room. */
	BOOK_ROOM(new BookRoomCommand()),

	/** The search by date. */
	SEARCH_BY_DATE(new SearchByDateCommand()),

	/** The search by price. */
	SEARCH_BY_PRICE(new SearchByPriceCommand()),

	/** The get free rooms. */
	GET_FREE_ROOMS(new GetFreeRoomsCommand());

	/** The command. */
	ICommand command;

	/**
	 * Instantiates a new command enum.
	 *
	 * @param command
	 *            the command
	 */
	CommandEnum(ICommand command) {
		this.command = command;
	}

	/**
	 * Gets the current command.
	 *
	 * @return the current command
	 */
	public ICommand getCurrentCommand() {
		return command;
	}
}
