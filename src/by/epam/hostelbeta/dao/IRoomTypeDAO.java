package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.RoomType;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRoomTypeDAO.
 */
public interface IRoomTypeDAO {
	
	/**
	 * Find all room types.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<RoomType> findAllRoomTypes() throws DAOException;
}
