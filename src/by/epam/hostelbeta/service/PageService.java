package by.epam.hostelbeta.service;

import java.util.HashMap;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.HostelDAO;
import by.epam.hostelbeta.entity.Hostel;

public class PageService {
	public static HashMap<String, Object> getPageAttrubutes(String page) throws ServiceException {
		try {
			switch (page) {
			case "home":
				return getHomePageAttributes();
			default:
				return null;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	private static HashMap<String, Object> getHomePageAttributes() throws DAOException {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels = hostelDAO.findAllHostels();
		attributes.put("hostelList", hostels);
		hostelDAO.closeConnection();
		return attributes;
	}
}
