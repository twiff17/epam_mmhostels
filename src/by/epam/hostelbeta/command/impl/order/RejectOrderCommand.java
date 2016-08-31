package by.epam.hostelbeta.command.impl.order;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stringtemplate.v4.ST;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.mail.MailMessageTemplate;
import by.epam.hostelbeta.mail.MailThread;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

public class RejectOrderCommand extends AbstractCommand {
	private static final String HOSTEL = "hostel";
	private static final String IN_DATE = "inDate";
	private static final String OUT_DATE = "outDate";
	private static final String LOGIN = "login";
	private static final String MESSAGE_SUBJECT = "Заявка на бронирование отклонена";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
			OrderService.rejectOrder(orderId);
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
		message = new ST(MailMessageTemplate.rejectOrderMessage);
		message.add(LOGIN, order.getUserLogin());
		message.add(HOSTEL, order.getHostelName());
		message.add(IN_DATE, order.getInDate());
		message.add(OUT_DATE, order.getOutDate());
		MailThread mailOperator = new MailThread(order.getUserEmail(), MESSAGE_SUBJECT, message.render(), properties);

		mailOperator.start();
	}
}
