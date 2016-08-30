package by.epam.hostelbeta.command.impl.order;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.domain.entity.Order;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.mail.MailMessageTemplate;
import by.epam.hostelbeta.mail.MailThread;
import by.epam.hostelbeta.service.HostelService;
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

			User user = (User) request.getSession().getAttribute(Parameters.SESSION_USER);

			long userId = user.getUserId();
			boolean discount = user.isDiscount();

			order.setHostelId(hostelId);
			order.setRoomId(roomId);
			order.setInDate(inDate);
			order.setOutDate(outDate);
			order.setUserId(userId);
			order.setBooking(booking);

			if (OrderService.bookRoom(order, discount)) {
				Hostel hostel = HostelService.getHostelById(hostelId);
				sendMail(request, hostel, order , user);
				message = locManager.getResourceBundle().getString(Parameters.ROOM_IS_BOOKED_SUCCESSFUL);
			} else {
				message = locManager.getResourceBundle().getString(Parameters.ROOM_BOOKING_FAIL);
			}

		} catch (ServiceException | IOException e) {
			throw new CommandException(e);
		}
		return message;
	}

	private void sendMail(HttpServletRequest request, Hostel hostel, Order order, User user) throws IOException {
		Properties properties = new Properties();
		ServletContext context = request.getServletContext();
		String filename = context.getInitParameter(Parameters.MAIL);
		properties.load(context.getResourceAsStream(filename));
		ST message;
		message = new ST(MailMessageTemplate.bookRoomMessage);
		message.add("inDate", order.getInDate());
		message.add("outDate", order.getOutDate());
		message.add("login", user.getLogin());
		message.add("hostel", hostel.getName());
		MailThread mailOperator = new MailThread(user.getEmail(), "«а€вка на бронирование в очереди на обработку.",
				message.render(), properties);

		mailOperator.start();
	}
}
