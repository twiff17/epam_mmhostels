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
 * The Class GetRoomsCommand.
 */
public class GetRoomsCommand extends AbstractCommand {
	
	/** The Constant ROOMS_PATH. */
	private static final String ROOMS_PATH = "path.page.rooms";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			List<RoomDTO> rooms = RoomService
					.getRoomsByHostelId(Long.parseLong(request.getParameter(Parameters.HOSTEL_ID)));
			request.setAttribute(Parameters.ROOM_LIST, rooms);
			request.setAttribute(Parameters.HOSTEL_NAME, request.getParameter(Parameters.HOSTEL_NAME));
			return ConfigurationManager.getProperty(ROOMS_PATH);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + ROOMS_PATH, e);
		}

	}

}
