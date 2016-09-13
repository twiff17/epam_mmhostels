package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.RoomType;

/**
 * The Interface IRoomTypeDAO.
 */
public interface IRoomTypeDAO {

	/**
	 * Find all room types.
	 *
	 * @return the list of all types
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<RoomType> findAllRoomTypes() throws DAOException;
}
