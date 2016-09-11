package by.epam.hostelbeta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.HostelDAO;
import by.epam.hostelbeta.dao.impl.OrderDAO;
import by.epam.hostelbeta.dao.impl.RoomDAO;
import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Hostel;

// TODO: Auto-generated Javadoc
/**
 * The Class HostelService.
 */
public class HostelService {
	
	/** The Constant RECORDS_PER_PAGE. */
	private static final int RECORDS_PER_PAGE = 5;

	/**
	 * Gets the popular hostels.
	 *
	 * @return the popular hostels
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Gets the all hostels.
	 *
	 * @param pageNumber the page number
	 * @param hostels the hostels
	 * @return the all hostels
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Gets the all hostels.
	 *
	 * @return the all hostels
	 * @throws ServiceException the service exception
	 */
	public static List<Hostel> getAllHostels() throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		try {
			return hostelDAO.findAllHostels();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Delete hostel.
	 *
	 * @param hostelId the hostel id
	 * @throws ServiceException the service exception
	 */
	public static void deleteHostel(long hostelId) throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		RoomDAO roomDAO = new RoomDAO();
		try {
			roomDAO.deleteRoomsByHostelId(hostelId);
			hostelDAO.deleteHostel(hostelId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Adds the hostel.
	 *
	 * @param hostel the hostel
	 * @throws ServiceException the service exception
	 */
	public static void addHostel(Hostel hostel) throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		try {
			hostelDAO.addHostel(hostel);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the hostel by id.
	 *
	 * @param hostelId the hostel id
	 * @return the hostel by id
	 * @throws ServiceException the service exception
	 */
	public static Hostel getHostelById(long hostelId) throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		try {
			return hostelDAO.findHostelById(hostelId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Edits the hostel.
	 *
	 * @param hostel the hostel
	 * @throws ServiceException the service exception
	 */
	public static void editHostel(Hostel hostel) throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		try {
			hostelDAO.editHostel(hostel);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Search by date and country.
	 *
	 * @param country the country
	 * @param inDate the in date
	 * @param outDate the out date
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	public static List<HostelDTO> searchByDateAndCountry(String country, LocalDate inDate, LocalDate outDate)
			throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		RoomDAO roomDAO = new RoomDAO();
		OrderDAO orderDAO = new OrderDAO();
		List<HostelDTO> hostels = new ArrayList<HostelDTO>();
		List<HostelDTO> resultHostels = new ArrayList<HostelDTO>();
		try {
			hostels = hostelDAO.findByCountry(country);
			for (HostelDTO hostel : hostels) {
				List<RoomDTO> rooms = roomDAO.findRoomsByHostelId(hostel.getHostelId());
				for (RoomDTO room : rooms) {
					if (!orderDAO.checkRoom(room, inDate, outDate)) {
						resultHostels.add(hostel);
						break;
					}
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return resultHostels;
	}

	/**
	 * Search by price and country.
	 *
	 * @param country the country
	 * @param minPrice the min price
	 * @param maxPrice the max price
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	public static List<HostelDTO> searchByPriceAndCountry(String country, int minPrice, int maxPrice)
			throws ServiceException {
		HostelDAO hostelDAO = new HostelDAO();
		try {
			return hostelDAO.findHostelsByPrice(country, minPrice, maxPrice);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
