package by.epam.hostelbeta.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.HostelDAO;
import by.epam.hostelbeta.entity.Hostel;

public class PageService {
	private static final int HOSTEL_RECORDS_PER_PAGE = 5;

	public static HashMap<String, Object> getPageAttrubutes(String page, Map<String, String[]> params)
			throws ServiceException {
		switch (page) {
		case "home":
			return getHomePageAttributes(params);
		default:
			return null;
		}
	}

	private static HashMap<String, Object> getHomePageAttributes(Map<String, String[]> params) throws ServiceException {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels;
		int page = 1;
		if (params.get("pageNumber") != null)
			page = Integer.parseInt(params.get("pageNumber")[0]);
		try {
			hostels = hostelDAO.findAllHostels((page-1)*HOSTEL_RECORDS_PER_PAGE,
					HOSTEL_RECORDS_PER_PAGE);
			int noOfRecords = hostelDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / HOSTEL_RECORDS_PER_PAGE);
			attributes.put("hostelList", hostels);
			attributes.put("noOfPages", noOfPages);
	        attributes.put("currentPage", page);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} finally {
			hostelDAO.closeConnection();
		}
		return attributes;
	}
}
