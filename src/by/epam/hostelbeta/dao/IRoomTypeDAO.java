package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.RoomType;

public interface IRoomTypeDAO {
	List<RoomType> findAllRoomTypes() throws DAOException;
}
