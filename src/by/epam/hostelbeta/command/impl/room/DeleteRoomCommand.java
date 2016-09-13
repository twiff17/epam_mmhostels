package by.epam.hostelbeta.command.impl.room;

import java.time.LocalDate;
import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.RoomService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class DeleteRoomCommand.
 * Deletes room
 */
public class DeleteRoomCommand extends AbstractCommand {
	
	/** The Constant PROPERTY_NO_FOUND. */
	private static final String PROPERTY_NO_FOUND = "???not_found???";

	/** The Constant STATUS_IN_PROCESSING. */
	private static final String STATUS_IN_PROCESSING = "В обработке";
	
	/** The Constant STATUS_ACCEPTED. */
	private static final String STATUS_ACCEPTED = "Принят";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long hostelId = Long.parseLong(request.getParameter(Parameters.HOSTEL_ID));
			long roomId = Long.parseLong(request.getParameter(Parameters.ROOM_ID));

			List<OrderDTO> orders = OrderService.getOrdersByRoomId(hostelId, roomId);
			for (OrderDTO order : orders) {
				if (LocalDate.now().isBefore(order.getInDate()) && (STATUS_IN_PROCESSING.equals(order.getStatus())
						|| STATUS_ACCEPTED.equals(order.getStatus()))) {
					return locManager.getResourceBundle().getString(Parameters.CANT_DELETE_ROOM);
				}
			}

			RoomService.deleteRoom(hostelId, roomId);

			message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			message = PROPERTY_NO_FOUND;
		}
		return message;
	}
}
