package by.epam.hostelbeta.command.impl.common;

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

/**
 * The Class GetCabinetCommand. Fills user's order list and returns the page
 * with this list.
 */
public class GetCabinetCommand extends AbstractCommand {

	/** The Constant CABINET. The name of current page */
	private static final String CABINET = "cabinet";

	/** The Constant CABINET_PATH. The path to the cabinet page */
	private static final String CABINET_PATH = "path.page.cabinet";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		try {
			orders = OrderService.getOrdersByUserId(Long.parseLong(request.getParameter(Parameters.USER_ID)));
			request.setAttribute(Parameters.ORDER_LIST, orders);
			request.getSession().setAttribute(Parameters.PAGE, CABINET);
			return ConfigurationManager.getProperty(CABINET_PATH);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + CABINET_PATH, e);
		}
	}
}
