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

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.HostelDAO;
import by.epam.hostelbeta.entity.Hostel;

@WebFilter("/index.jsp")
public class IndexFilter implements Filter {
    public IndexFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels;
		int page = 1;
		try {
			hostels = hostelDAO.findAllHostels((page-1)*5,
					5);
			int noOfRecords = hostelDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 5);
	        request.setAttribute("hostelList", hostels);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
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
