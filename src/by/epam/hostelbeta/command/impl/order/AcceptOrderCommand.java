package by.epam.hostelbeta.command.impl.order;

import java.io.IOException;
import java.util.MissingResourceException;
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

// TODO: Auto-generated Javadoc
/**
 * The Class AcceptOrderCommand.
 */
public class AcceptOrderCommand extends AbstractCommand {
	
	/** The Constant PRICE. */
	private static final String PRICE = "price";
	
	/** The Constant HOSTEL. */
	private static final String HOSTEL = "hostel";
	
	/** The Constant IN_DATE. */
	private static final String IN_DATE = "inDate";
	
	/** The Constant OUT_DATE. */
	private static final String OUT_DATE = "outDate";
	
	/** The Constant LOGIN. */
	private static final String LOGIN = "login";
	
	/** The Constant MESSAGE_SUBJECT. */
	private static final String MESSAGE_SUBJECT = "Заявка на бронирование принята";
	
	/** The Constant PROPERTY_NO_FOUND. */
	private static final String PROPERTY_NO_FOUND = "???not_found???";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
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
		} catch (MissingResourceException e) {
			message = PROPERTY_NO_FOUND;
		}
		return message;
	}

	/**
	 * Send mail.
	 *
	 * @param request the request
	 * @param order the order
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
			message.add(PRICE, order.getPrice());
		}
		message.add(LOGIN, order.getUserLogin());
		message.add(HOSTEL, order.getHostelName());
		message.add(IN_DATE, order.getInDate());
		message.add(OUT_DATE, order.getOutDate());
		MailThread mailOperator = new MailThread(order.getUserEmail(), MESSAGE_SUBJECT, message.render(), properties);

		mailOperator.start();
	}
}
