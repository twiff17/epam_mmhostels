package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.entity.Hostel;

public interface IHostelDAO {
	List<HostelDTO> findAllHostels(int offset, int noOfRecords) throws DAOException;
	List<Hostel> findPopularHostels() throws DAOException;
}
