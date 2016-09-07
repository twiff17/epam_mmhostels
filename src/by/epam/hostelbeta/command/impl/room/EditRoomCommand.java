package by.epam.hostelbeta.command.impl.room;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.domain.entity.Room;
import by.epam.hostelbeta.domain.entity.RoomType;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.RoomService;
import by.epam.hostelbeta.service.RoomTypeService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.validator.RoomValidator;

public class EditRoomCommand extends AbstractCommand {
	private static final String ROOM_PATH = "path.page.room";
	private static final String ROOM_ADD_PATH = "path.page.room-add";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		Room room = new Room();
		try {
			room.setHostelId(Long.parseLong(request.getParameter(Parameters.HOSTEL_ID)));
			room.setRoomId(Long.parseLong(request.getParameter(Parameters.ROOM_ID)));
			room.setBedsNumber(Integer.parseInt(request.getParameter(Parameters.BEDS_NUMBER)));
			room.setRoomType(Integer.parseInt(request.getParameter(Parameters.ROOM_TYPE)));

			if (RoomValidator.validate(room)) {
				RoomService.editRoom(room);
				List<RoomDTO> rooms = RoomService.getAllRooms();
				request.setAttribute(Parameters.ROOM_LIST, rooms);
				page = ConfigurationManager.getProperty(ROOM_PATH);
			} else {
				List<Hostel> hostels = HostelService.getAllHostels();
				List<RoomType> roomTypes = RoomTypeService.getAllTypes();
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				request.setAttribute(Parameters.ROOM_TYPE_LIST, roomTypes);
				request.setAttribute(Parameters.ERROR_ADD_ROOM_MESSAGE,
						locManager.getResourceBundle().getString(Parameters.INVALID_DATA));
				request.setAttribute(Parameters.ROOM, room);
				page = ConfigurationManager.getProperty(ROOM_ADD_PATH);
			}
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path in properties file", e);
		}
		return page;
	}
}
