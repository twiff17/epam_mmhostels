package by.epam.hostelbeta.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.HostelDAO;
import by.epam.hostelbeta.domain.entity.Hostel;

public class RequestUtil {
	private static final int RECORDS_PER_PAGE = 5;

	public static String createHomePage(HttpServletRequest request) throws DAOException {
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels;
		hostels = hostelDAO.findPopularHostels(0, RECORDS_PER_PAGE);
		int noOfRecords = hostelDAO.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
		request.setAttribute(Parameters.HOSTEL_LIST, hostels);
		request.setAttribute(Parameters.NO_OF_PAGES, noOfPages);
		request.setAttribute(Parameters.CURRENT_PAGE, 1);
		request.getSession().setAttribute(Parameters.PAGE, Parameters.HOME);
		return ConfigurationManager.getProperty(Parameters.HOME_PATH);
	}
}
