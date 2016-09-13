package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.entity.Hostel;

/**
 * The Interface IHostelDAO.
 */
public interface IHostelDAO {

	/**
	 * Finds all hostels by pages.
	 *
	 * @param offset
	 *            The offset
	 * @param noOfRecords
	 *            The no of records. How much records will be selected
	 * @return the list of hostels
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<HostelDTO> findAllHostels(int offset, int noOfRecords) throws DAOException;

	/**
	 * Find popular hostels.
	 *
	 * @return the list of popular hostels
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<Hostel> findPopularHostels() throws DAOException;

	/**
	 * Find all hostels.
	 *
	 * @return the list of all hostels
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<Hostel> findAllHostels() throws DAOException;

	/**
	 * Deletes hostel.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void deleteHostel(long hostelId) throws DAOException;

	/**
	 * Adds the hostel.
	 *
	 * @param hostel
	 *            the hostel
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void addHostel(Hostel hostel) throws DAOException;

	/**
	 * Find hostel by id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @return the found hostel, or null if hostel wasn't found
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	Hostel findHostelById(long hostelId) throws DAOException;

	/**
	 * Edits the hostel.
	 *
	 * @param hostel
	 *            the hostel
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void editHostel(Hostel hostel) throws DAOException;

	/**
	 * Find hostels by country.
	 *
	 * @param country
	 *            the country
	 * @return the list of found hostels
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<HostelDTO> findByCountry(String country) throws DAOException;

	/**
	 * Find hostels by price.
	 *
	 * @param country
	 *            the country
	 * @param minPrice
	 *            the min price
	 * @param maxPrice
	 *            the max price
	 * @return the list of found hostels
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<HostelDTO> findHostelsByPrice(String country, int minPrice, int maxPrice) throws DAOException;
}
