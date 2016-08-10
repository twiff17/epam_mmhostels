package by.epam.hostelbeta.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.NotNull;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.HostelDAO;
import by.epam.hostelbeta.entity.Hostel;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class EmptyCommand extends AbstractCommand implements ICommand {
	private static final int HOSTEL_RECORDS_PER_PAGE = 5;
	
	@Override
	
	public String execute(HttpServletRequest request, HttpServletResponse response){
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels;
		int page = 1;
		try {
			hostels = hostelDAO.findPopularHostels((page - 1) * HOSTEL_RECORDS_PER_PAGE, HOSTEL_RECORDS_PER_PAGE);
			int noOfRecords = hostelDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / HOSTEL_RECORDS_PER_PAGE);
			httpRequest.setAttribute(Parameters.HOSTEL_LIST, hostels);
			httpRequest.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
			httpRequest.setAttribute(Parameters.CURRENT_PAGE, page);
			httpRequest.getSession().setAttribute(Parameters.PAGE, Parameters.HOME);
		} catch (DAOException e) {
			throw new RuntimeException(e);
		} finally {
			hostelDAO.closeConnection();
		}
		return ConfigurationManager.getProperty(Parameters.HOME_PATH);
	}
}
