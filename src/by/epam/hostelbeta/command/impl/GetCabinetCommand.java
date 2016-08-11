package by.epam.hostelbeta.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.OrderDAO;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetCabinetCommand extends AbstractCommand {

	private static final int RECORDS_PER_PAGE = 5;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		OrderDAO orderDAO = new OrderDAO();
		List<OrderDTO> orders;
		int page = 1;
		if (request.getParameter(Parameters.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(Parameters.PAGE_NUMBER));
		}
		try {
			orders = orderDAO.findOrdersByUserId(Long.parseLong(request.getParameter(Parameters.USER_ID)),
					(page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
			int noOfRecords = orderDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
			request.setAttribute(Parameters.ORDER_LIST, orders);
			request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
			request.setAttribute(Parameters.CURRENT_PAGE, page);
			request.getSession().setAttribute(Parameters.PAGE, Parameters.CABINET);
		} catch (NumberFormatException | DAOException e) {
			throw new CommandException(e);
		}

		return ConfigurationManager.getProperty(Parameters.CABINET_PATH);
	}
}
