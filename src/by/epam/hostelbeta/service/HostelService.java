package by.epam.hostelbeta.service;

import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.HostelDAO;
import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.entity.Hostel;

public class HostelService {
	private static final int RECORDS_PER_PAGE = 5;

	public static List<Hostel> getPopularHostels() throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		List<Hostel> hostels = new ArrayList<Hostel>();
		try {
			hostels = hostelDAO.findPopularHostels();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return hostels;
	}

	public static int getAllHostels(int pageNumber, List<HostelDTO> hostels) throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		try {
			hostels.addAll(hostelDAO.findAllHostels((pageNumber - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE));
			int noOfRecords = hostelDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
			return noOfPages;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public static List<Hostel> getAllHostels() throws ServiceException{
		HostelDAO hostelDAO = new HostelDAO();
		try{
			return hostelDAO.findAllHostels();
		}catch(DAOException e){
			throw new ServiceException(e);
		}
	}
}
