package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.entity.Hostel;

public interface IHostelDAO {
	List<HostelDTO> findAllHostels(int offset, int noOfRecords) throws DAOException;

	List<Hostel> findPopularHostels() throws DAOException;

	List<Hostel> findAllHostels() throws DAOException;

	void deleteHostel(long hostelId) throws DAOException;

	void addHostel(Hostel hostel) throws DAOException;

	Hostel findHostelById(long hostelId) throws DAOException;

	void editHostel(Hostel hostel) throws DAOException;
}
