package by.epam.hostelbeta.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.HostelDAO;
import by.epam.hostelbeta.entity.Hostel;
import by.epam.hostelbeta.util.Parameters;

@WebFilter("/index.jsp")
public class IndexFilter implements Filter {
	private static final int HOSTEL_RECORDS_PER_PAGE = 5;

	public IndexFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
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
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
