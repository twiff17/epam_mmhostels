package by.epam.hostelbeta.command.impl.room;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.RoomService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.validator.OrderValidator;

public class GetFreeRoomsCommand extends AbstractCommand {
	private static final String ROOMS_PATH = "path.page.rooms";
	private static final String HOSTELS = "hostels";
	private static final String HOSTELS_PATH = "path.page.hostels";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String page = null;
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		try {
			LocalDate inDate = LocalDate.parse(request.getParameter(Parameters.IN_DATE));
			LocalDate outDate = LocalDate.parse(request.getParameter(Parameters.OUT_DATE));

			request.setAttribute(Parameters.PAGE, HOSTELS);
			int validationResult = OrderValidator.dateValidate(inDate, outDate);
			if (validationResult == 0) {
				long hostelId = Long.parseLong(request.getParameter(Parameters.HOSTEL_ID));
				List<RoomDTO> rooms = RoomService.getFreeRooms(hostelId, inDate, outDate);
				request.setAttribute(Parameters.ROOM_LIST, rooms);
				request.setAttribute(Parameters.IN_DATE, inDate);
				request.setAttribute(Parameters.OUT_DATE, outDate);
				request.setAttribute(Parameters.HOSTEL_NAME, request.getParameter(Parameters.HOSTEL_NAME));
				page = ConfigurationManager.getProperty(ROOMS_PATH);
			} else {
				List<HostelDTO> hostels = new ArrayList<HostelDTO>();
				int noOfPages = HostelService.getAllHostels(1, hostels);
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
				request.setAttribute(Parameters.CURRENT_PAGE, 1);
				if (validationResult == 1) {
					request.setAttribute(Parameters.ERROR_MESSAGE,
							locManager.getResourceBundle().getString(Parameters.IN_DATE_AFTER_OUT));
				} else {
					request.setAttribute(Parameters.ERROR_MESSAGE,
							locManager.getResourceBundle().getString(Parameters.DATE_BEFORE_TODAY));
				}
				page = ConfigurationManager.getProperty(HOSTELS_PATH);
			}
		} catch (ServiceException | NumberFormatException | DateTimeParseException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path in properties file", e);
		}
		return page;
	}
}
