package by.epam.hostelbeta.service;

import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.HostelDAO;
import by.epam.hostelbeta.domain.entity.Hostel;

public class HostelService {
	public static List<Hostel> getPopularHostels() throws ServiceException{
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels;
		try {
			hostels = hostelDAO.findPopularHostels();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return hostels;
	}
}
