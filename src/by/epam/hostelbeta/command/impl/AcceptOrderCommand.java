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

public class AcceptOrderCommand extends AbstractCommand{
	private static final String ORDERS_PATH = "path.page.orders";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		try {
			long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
			OrderService.acceptOrder(orderId);
			OrderService.getAllOrders(1, orders);
			request.setAttribute(Parameters.ORDER_LIST, orders);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}catch(NumberFormatException e){
			throw new CommandException("Invalid orderId value", e);
		}
		return ConfigurationManager.getProperty(ORDERS_PATH);
	}
	
}
