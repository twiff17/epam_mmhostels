package by.epam.hostelbeta.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.HostelDAO;
import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetHostelsCommand extends AbstractCommand {
	private static final String HOSTELS = "hostels";
	private static final String HOSTELS_PATH = "path.page.hostels";
	
	private static final int RECORDS_PER_PAGE = 5;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		HostelDAO hostelDAO = new HostelDAO();
		List<HostelDTO> hostels;
		int page = 1;
		if (request.getParameter(Parameters.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(Parameters.PAGE_NUMBER));
		}
		try {
			hostels = hostelDAO.findAllHostels((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE);
			int noOfRecords = hostelDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
			request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
			request.setAttribute(Parameters.CURRENT_PAGE, page);
			request.getSession().setAttribute(Parameters.PAGE, HOSTELS);
		} catch (DAOException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty(HOSTELS_PATH);
	}

}
