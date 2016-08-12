package by.epam.hostelbeta.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.service.RoomService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetRoomsCommand extends AbstractCommand {
	private static final String ROOMS_PATH = "path.page.rooms";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try{
			List<RoomDTO> rooms = RoomService.getRoomsByHostelId(Long.parseLong(request.getParameter(Parameters.HOSTEL_ID)));
			request.setAttribute(Parameters.ROOM_LIST, rooms);
			request.setAttribute(Parameters.HOSTEL_NAME, request.getParameter(Parameters.HOSTEL_NAME));
		}catch(ServiceException e){
			throw new CommandException(e);
		}catch(NumberFormatException e){
			throw new CommandException("Invalid hostelId value from jsp", e);
		}
		return ConfigurationManager.getProperty(ROOMS_PATH);
	}

}
