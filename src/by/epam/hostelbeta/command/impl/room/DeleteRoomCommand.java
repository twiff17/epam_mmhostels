package by.epam.hostelbeta.command.impl.room;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.mail.MailMessageTemplate;
import by.epam.hostelbeta.mail.MailThread;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.RoomService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class DeleteRoomCommand extends AbstractCommand {
	private static final String PROPERTY_NO_FOUND = "???not_found???";
	private static final String STATUS_ACCEPTED = "Принят";
	private static final String STATUS_IN_QUEUE = "В обработке";

	private static final String HOSTEL = "hostel";
	private static final String IN_DATE = "inDate";
	private static final String OUT_DATE = "outDate";
	private static final String LOGIN = "login";
	private static final String MESSAGE_SUBJECT = "Ваш заказ аннулирован.";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long hostelId = Long.parseLong(request.getParameter(Parameters.HOSTEL_ID));
			long roomId = Long.parseLong(request.getParameter(Parameters.ROOM_ID));

			Hostel hostel = HostelService.getHostelById(hostelId);

			List<OrderDTO> orders = OrderService.getOrdersByRoomId(hostelId, roomId);
			for (OrderDTO order : orders) {
				if (LocalDate.now().isBefore(order.getInDate())) {
					String messageTemplate = null;
					if ((STATUS_ACCEPTED.equals(order.getStatus()) && order.isBooking())
							|| STATUS_IN_QUEUE.equals(order.getStatus())) {
						System.out.println(order.getStatus());
						messageTemplate = MailMessageTemplate.roomDeletedBookingOrInQueue;
						sendMail(request, hostel, order, messageTemplate);
					} else if (STATUS_ACCEPTED.equals(order.getStatus()) && !order.isBooking()) {
						messageTemplate = MailMessageTemplate.roomDeletedFullpayment;
						sendMail(request, hostel, order, messageTemplate);
					}
				}
			}

			RoomService.deleteRoom(hostelId, roomId);

			message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
		} catch (ServiceException | NumberFormatException | IOException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			message = PROPERTY_NO_FOUND;
		}
		return message;
	}

	private void sendMail(HttpServletRequest request, Hostel hostel, OrderDTO order, String messageTemplate)
			throws IOException {
		Properties properties = new Properties();
		ServletContext context = request.getServletContext();
		String filename = context.getInitParameter(Parameters.MAIL);
		properties.load(context.getResourceAsStream(filename));
		ST message;
		message = new ST(messageTemplate);
		message.add(IN_DATE, order.getInDate());
		message.add(OUT_DATE, order.getOutDate());
		message.add(LOGIN, order.getUserLogin());
		message.add(HOSTEL, hostel.getName());
		MailThread mailOperator = new MailThread(order.getUserEmail(), MESSAGE_SUBJECT, message.render(), properties);

		mailOperator.start();
	}
}
