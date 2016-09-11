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

// TODO: Auto-generated Javadoc
/**
 * The Class RejectOrderCommand.
 */
public class RejectOrderCommand extends AbstractCommand {
	
	/** The Constant ORDERS_PATH. */
	private static final String ORDERS_PATH = "path.page.order";
	
	/** The Constant ADMIN. */
	private static final String ADMIN = "admin";

	/** The Constant STATUS_ACCEPTED. */
	private static final String STATUS_ACCEPTED = "Принят";

	/** The Constant HOSTEL. */
	private static final String HOSTEL = "hostel";
	
	/** The Constant IN_DATE. */
	private static final String IN_DATE = "inDate";
	
	/** The Constant OUT_DATE. */
	private static final String OUT_DATE = "outDate";
	
	/** The Constant LOGIN. */
	private static final String LOGIN = "login";
	
	/** The Constant MESSAGE_SUBJECT. */
	private static final String MESSAGE_SUBJECT = "Заявка на бронирование отклонена";
	
	/** The Constant CAUSE. */
	private static final String CAUSE = "cause";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			String cause = request.getParameter(Parameters.CAUSE);
			long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
			OrderDTO order = OrderService.getOrderById(orderId);
			OrderService.rejectOrder(orderId);

			List<OrderDTO> orders = OrderService.getAllOrders();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.ORDER_LIST, orders);

			sendMail(request, order, cause);
			return ConfigurationManager.getProperty(ORDERS_PATH);
		} catch (ServiceException | NumberFormatException | IOException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + ORDERS_PATH, e);
		}
	}

	/**
	 * Send mail.
	 *
	 * @param request the request
	 * @param order the order
	 * @param cause the cause
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void sendMail(HttpServletRequest request, OrderDTO order, String cause) throws IOException {
		Properties properties = new Properties();
		ServletContext context = request.getServletContext();
		String filename = context.getInitParameter(Parameters.MAIL);
		properties.load(context.getResourceAsStream(filename));
		ST message = new ST(MailMessageTemplate.rejectBookingOrderMessage);
		if (!order.isBooking() && STATUS_ACCEPTED.equals(order.getStatus())) {
			message = new ST(MailMessageTemplate.rejectFullpaymentOrderMessage);
		}
		message.add(LOGIN, order.getUserLogin());
		message.add(HOSTEL, order.getHostelName());
		message.add(IN_DATE, order.getInDate());
		message.add(OUT_DATE, order.getOutDate());
		message.add(CAUSE, cause);
		MailThread mailOperator = new MailThread(order.getUserEmail(), MESSAGE_SUBJECT, message.render(), properties);

		mailOperator.start();
	}
}
