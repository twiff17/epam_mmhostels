package by.epam.hostelbeta.command.impl.order;

import java.io.IOException;
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
import by.epam.hostelbeta.mail.MailMessageTemplate;
import by.epam.hostelbeta.mail.MailThread;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class RejectOrderCommand extends AbstractCommand {
	private static final String ORDERS_PATH = "path.page.order";
	private static final String ADMIN = "admin";

	private static final String HOSTEL = "hostel";
	private static final String IN_DATE = "inDate";
	private static final String OUT_DATE = "outDate";
	private static final String LOGIN = "login";
	private static final String MESSAGE_SUBJECT = "Заявка на бронирование отклонена";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			System.out.println(request.getParameter("cause"));
			long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
			OrderDTO order = OrderService.getOrderById(orderId);
			OrderService.rejectOrder(orderId);

			List<OrderDTO> orders = OrderService.getAllOrders();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.ORDER_LIST, orders);

			sendMail(request, order);
			return ConfigurationManager.getProperty(ORDERS_PATH);
		} catch (ServiceException | NumberFormatException | IOException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + ORDERS_PATH, e);
		}
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
