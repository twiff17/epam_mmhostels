package by.epam.hostelbeta.command.impl.room;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.domain.entity.RoomType;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.RoomTypeService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class GetRoomAddCommand. Returns the adding room page
 */
public class GetRoomAddCommand extends AbstractCommand {

	/** The Constant ROOM_ADD_PAGE. The path of the adding room page */
	private static final String ROOM_ADD_PAGE = "path.page.room-add";

	/** The Constant ADMIN. The name of the current page */
	private static final String ADMIN = "admin";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			List<Hostel> hostels = HostelService.getAllHostels();
			List<RoomType> roomTypes = RoomTypeService.getAllTypes();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			request.setAttribute(Parameters.ROOM_TYPE_LIST, roomTypes);
			return ConfigurationManager.getProperty(ROOM_ADD_PAGE);
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + ROOM_ADD_PAGE, e);
		}
	}
}
