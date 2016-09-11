package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.entity.Hostel;

// TODO: Auto-generated Javadoc
/**
 * The Interface IHostelDAO.
 */
public interface IHostelDAO {
	
	/**
	 * Find all hostels.
	 *
	 * @param offset the offset
	 * @param noOfRecords the no of records
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<HostelDTO> findAllHostels(int offset, int noOfRecords) throws DAOException;

	/**
	 * Find popular hostels.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<Hostel> findPopularHostels() throws DAOException;

	/**
	 * Find all hostels.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<Hostel> findAllHostels() throws DAOException;

	/**
	 * Delete hostel.
	 *
	 * @param hostelId the hostel id
	 * @throws DAOException the DAO exception
	 */
	void deleteHostel(long hostelId) throws DAOException;

	/**
	 * Adds the hostel.
	 *
	 * @param hostel the hostel
	 * @throws DAOException the DAO exception
	 */
	void addHostel(Hostel hostel) throws DAOException;

	/**
	 * Find hostel by id.
	 *
	 * @param hostelId the hostel id
	 * @return the hostel
	 * @throws DAOException the DAO exception
	 */
	Hostel findHostelById(long hostelId) throws DAOException;

	/**
	 * Edits the hostel.
	 *
	 * @param hostel the hostel
	 * @throws DAOException the DAO exception
	 */
	void editHostel(Hostel hostel) throws DAOException;

	/**
	 * Find by country.
	 *
	 * @param country the country
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<HostelDTO> findByCountry(String country) throws DAOException;

	/**
	 * Find hostels by price.
	 *
	 * @param country the country
	 * @param minPrice the min price
	 * @param maxPrice the max price
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<HostelDTO> findHostelsByPrice(String country, int minPrice, int maxPrice) throws DAOException;
}
