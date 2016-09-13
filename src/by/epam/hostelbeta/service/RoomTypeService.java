package by.epam.hostelbeta.service;

import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.RoomTypeDAO;
import by.epam.hostelbeta.domain.entity.RoomType;

/**
 * The Class RoomTypeService.
 */
public class RoomTypeService {

	/**
	 * Gets the all room types.
	 *
	 * @return the list of all types
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<RoomType> getAllTypes() throws ServiceException {
		RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
		List<RoomType> roomTypes = new ArrayList<RoomType>();
		try {
			roomTypes = roomTypeDAO.findAllRoomTypes();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return roomTypes;
	}
}
