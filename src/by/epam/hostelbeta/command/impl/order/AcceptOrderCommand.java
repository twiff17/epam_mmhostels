package by.epam.hostelbeta.command.impl.order;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.mail.MailMessageTemplate;
import by.epam.hostelbeta.mail.MailThread;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

import org.stringtemplate.v4.*;

public class AcceptOrderCommand extends AbstractCommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
			OrderService.acceptOrder(orderId);
			OrderDTO order = OrderService.getOrderById(orderId);

			sendMail(request, order);

			message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
		} catch (ServiceException | NumberFormatException | IOException e) {
			throw new CommandException(e);
		}
		return message;
	}

	private void sendMail(HttpServletRequest request, OrderDTO order) throws IOException {
		Properties properties = new Properties();
		ServletContext context = request.getServletContext();
		String filename = context.getInitParameter(Parameters.MAIL);
		properties.load(context.getResourceAsStream(filename));
		ST message;
		if (order.isBooking()) {
			message = new ST(MailMessageTemplate.acceptMessageBooking);
		} else {
			message = new ST(MailMessageTemplate.acceptMessageFullpayment);
			message.add("price", order.getPrice());
		}
		message.add("login", order.getUserLogin());
		message.add("hostel", order.getHostelName());
		message.add("inDate", order.getInDate());
		message.add("outDate", order.getOutDate());
		MailThread mailOperator = new MailThread(order.getUserEmail(), "Заявка на бронирование принята",
				message.render(), properties);

		mailOperator.start();
	}
}
