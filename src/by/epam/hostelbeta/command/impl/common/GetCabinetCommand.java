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
		int page = 1;
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		if (request.getParameter(Parameters.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(Parameters.PAGE_NUMBER));
		}
		try {
			int noOfPages = OrderService.getOrdersByUserId(Long.parseLong(request.getParameter(Parameters.USER_ID)),
					page, orders);
			request.setAttribute(Parameters.ORDER_LIST, orders);
			request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
			request.setAttribute(Parameters.CURRENT_PAGE, page);
			request.getSession().setAttribute(Parameters.PAGE, CABINET);
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (NumberFormatException e) {
			throw new CommandException("Inorrect UserId value", e);
		}

		return ConfigurationManager.getProperty(CABINET_PATH);
	}
}
