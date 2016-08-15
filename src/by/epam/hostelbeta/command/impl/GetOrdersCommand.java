package by.epam.hostelbeta.command.impl;

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

	private static final String ORDERS_PATH = "path.page.orders";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		int page = 1;
		if (request.getParameter(Parameters.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(Parameters.PAGE_NUMBER));
		}
		try {
			int noOfPages = OrderService.getAllOrders(page, orders);
			request.setAttribute(Parameters.ORDER_LIST, orders);
			request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
			request.setAttribute(Parameters.CURRENT_PAGE, page);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty(ORDERS_PATH);
	}
	
}
