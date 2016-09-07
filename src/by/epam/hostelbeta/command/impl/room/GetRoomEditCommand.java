package by.epam.hostelbeta.command.impl.room;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.domain.entity.Room;
import by.epam.hostelbeta.domain.entity.RoomType;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.RoomService;
import by.epam.hostelbeta.service.RoomTypeService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetRoomEditCommand extends AbstractCommand {

	private static final String ROOM_ADD_PAGE = "path.page.room-add";
	private static final String ADMIN = "admin";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			long hostelId = Long.parseLong(request.getParameter(Parameters.HOSTEL_ID));
			long roomId = Long.parseLong(request.getParameter(Parameters.ROOM_ID));
			Room room = RoomService.getRoomById(hostelId, roomId);

			List<Hostel> hostels = HostelService.getAllHostels();
			List<RoomType> roomTypes = RoomTypeService.getAllTypes();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			request.setAttribute(Parameters.ROOM_TYPE_LIST, roomTypes);
			request.setAttribute(Parameters.ROOM, room);
			return ConfigurationManager.getProperty(ROOM_ADD_PAGE);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + ROOM_ADD_PAGE, e);
		}

	}

}
