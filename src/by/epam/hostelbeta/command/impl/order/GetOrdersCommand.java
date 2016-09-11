package by.epam.hostelbeta.command.impl.order;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The Class GetOrdersCommand.
 */
public class GetOrdersCommand extends AbstractCommand {

	/** The Constant ORDERS_PATH. */
	private static final String ORDERS_PATH = "path.page.order";
	
	/** The Constant ADMIN. */
	private static final String ADMIN = "admin";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		try {
			orders = OrderService.getAllOrders();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.ORDER_LIST, orders);
			return ConfigurationManager.getProperty(ORDERS_PATH);
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + ORDERS_PATH, e);
		}

	}

}
