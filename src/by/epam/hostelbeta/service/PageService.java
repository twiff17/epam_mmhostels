package by.epam.hostelbeta.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.HostelDAO;
import by.epam.hostelbeta.dto.HostelDTO;
import by.epam.hostelbeta.entity.Hostel;
import by.epam.hostelbeta.util.Parameters;

public class PageService {
	private static final int HOSTEL_RECORDS_PER_PAGE = 5;

	public static HashMap<String, Object> getPageAttrubutes(String page, Map<String, String[]> params)
			throws ServiceException {
		switch (page) {
		case "home":
			return getHomePageAttributes(params);
		case "hostels":
			return getHostelsPageAttributes(params);
		default:
			return null;
		}
	}

	private static HashMap<String, Object> getHostelsPageAttributes(Map<String, String[]> params) throws ServiceException {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		HostelDAO hostelDAO = new HostelDAO();
		List<HostelDTO> hostels;
		int page = 1;
		if (params.get(Parameters.PAGE_NUMBER) != null)
			page = Integer.parseInt(params.get(Parameters.PAGE_NUMBER)[0]);
		try {
			hostels = hostelDAO.findAllHostels((page-1)*HOSTEL_RECORDS_PER_PAGE,
					HOSTEL_RECORDS_PER_PAGE);
			int noOfRecords = hostelDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / HOSTEL_RECORDS_PER_PAGE);
			attributes.put(Parameters.HOSTEL_LIST, hostels);
			attributes.put(Parameters.NO_OF_PAGES, noOfPages);
	        attributes.put(Parameters.CURRENT_PAGE, page);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			hostelDAO.closeConnection();
		}
		return attributes;
	}

	private static HashMap<String, Object> getHomePageAttributes(Map<String, String[]> params) throws ServiceException {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels;
		int page = 1;
		if (params.get(Parameters.PAGE_NUMBER) != null)
			page = Integer.parseInt(params.get(Parameters.PAGE_NUMBER)[0]);
		try {
			hostels = hostelDAO.findPopularHostels((page-1)*HOSTEL_RECORDS_PER_PAGE,
					HOSTEL_RECORDS_PER_PAGE);
			int noOfRecords = hostelDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / HOSTEL_RECORDS_PER_PAGE);
			attributes.put(Parameters.HOSTEL_LIST, hostels);
			attributes.put(Parameters.NO_OF_PAGES, noOfPages);
	        attributes.put(Parameters.CURRENT_PAGE, page);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			hostelDAO.closeConnection();
		}
		return attributes;
	}
}
