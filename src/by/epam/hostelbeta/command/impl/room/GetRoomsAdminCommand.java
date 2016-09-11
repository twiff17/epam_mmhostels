package by.epam.hostelbeta.command.impl.room;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.service.RoomService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The Class GetRoomsAdminCommand.
 */
public class GetRoomsAdminCommand extends AbstractCommand {
	
	/** The Constant ROOM_PATH. */
	private static final String ROOM_PATH = "path.page.room";
	
	/** The Constant ADMIN. */
	private static final String ADMIN = "admin";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			List<RoomDTO> rooms = RoomService.getAllRooms();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.ROOM_LIST, rooms);
			return ConfigurationManager.getProperty(ROOM_PATH);
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + ROOM_PATH, e);
		}
	}

}
