package by.epam.hostelbeta.command.impl.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetOrdersCommand extends AbstractCommand{

	private static final String ORDERS_PATH = "path.page.order";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		try {
			orders = OrderService.getAllOrders();
			request.setAttribute(Parameters.ORDER_LIST, orders);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty(ORDERS_PATH);
	}
	
}
