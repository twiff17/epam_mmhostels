package by.epam.hostelbeta.command.impl.order;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Order;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class BookRoomCommand extends AbstractCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String message = null;
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		Order order = new Order();
		try {
			LocalDate inDate = LocalDate.parse(request.getParameter(Parameters.IN_DATE));
			LocalDate outDate = LocalDate.parse(request.getParameter(Parameters.OUT_DATE));
			int booking = Integer.parseInt(request.getParameter(Parameters.BOOKING));
			long hostelId = Long.parseLong(request.getParameter(Parameters.HOSTEL_ID));
			int roomId = Integer.parseInt(request.getParameter(Parameters.ROOM_ID));

			order.setHostelId(hostelId);
			order.setRoomId(roomId);
			order.setInDate(inDate);
			order.setOutDate(outDate);
			order.setUserId((long)request.getSession().getAttribute(Parameters.USER_ID));
			order.setBooking(booking);

			if (OrderService.bookRoom(order)) {
				message = locManager.getResourceBundle().getString(Parameters.ROOM_IS_BOOKED_SUCCESSFUL);
			} else {
				message = locManager.getResourceBundle().getString(Parameters.ROOM_BOOKING_FAIL);
			}

		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return message;
	}

}
