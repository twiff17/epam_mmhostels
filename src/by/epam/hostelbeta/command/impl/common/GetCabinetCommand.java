package by.epam.hostelbeta.command.impl.common;

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

public class GetCabinetCommand extends AbstractCommand {
	private static final String CABINET = "cabinet";
	private static final String CABINET_PATH = "path.page.cabinet";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		try {
			orders = OrderService.getOrdersByUserId(Long.parseLong(request.getParameter(Parameters.USER_ID)));
			request.setAttribute(Parameters.ORDER_LIST, orders);
			request.getSession().setAttribute(Parameters.PAGE, CABINET);
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		}

		return ConfigurationManager.getProperty(CABINET_PATH);
	}
}
