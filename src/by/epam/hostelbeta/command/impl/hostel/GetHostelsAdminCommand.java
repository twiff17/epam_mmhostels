package by.epam.hostelbeta.command.impl.hostel;

import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class GetHostelsAdminCommand. Fills hostels list and returns hostels
 * management page
 */
public class GetHostelsAdminCommand extends AbstractCommand {

	/** The Constant HOSTEL_PATH. The path to the hostel management page */
	private static final String HOSTEL_PATH = "path.page.hostel";

	/** The Constant ADMIN. The name of the current page */
	private static final String ADMIN = "admin";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			List<Hostel> hostels = HostelService.getAllHostels();
			request.setAttribute(Parameters.PAGE, ADMIN);
			request.setAttribute(Parameters.HOSTEL_LIST, hostels);
			return ConfigurationManager.getProperty(HOSTEL_PATH);
		} catch (ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path " + HOSTEL_PATH, e);
		}

	}
}
