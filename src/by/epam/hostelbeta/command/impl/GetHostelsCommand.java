package by.epam.hostelbeta.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class GetHostelsCommand extends AbstractCommand {
	private static final String HOSTELS = "hostels";
	private static final String HOSTELS_PATH = "path.page.hostels";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		List<HostelDTO> hostels = new ArrayList<HostelDTO>();
		int page = 1;
		if (request.getParameter(Parameters.PAGE_NUMBER) != null) {
			page = Integer.parseInt(request.getParameter(Parameters.PAGE_NUMBER));
		}
		try {
			int noOfPages = HostelService.getAllHostels(page, hostels);
			request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
			request.setAttribute(Parameters.CURRENT_PAGE, page);
			request.getSession().setAttribute(Parameters.PAGE, HOSTELS);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty(HOSTELS_PATH);
	}

}
