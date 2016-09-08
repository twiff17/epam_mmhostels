package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Room;

public interface IRoomDAO {
	public List<RoomDTO> findRoomsByHostelId(long hostelId) throws DAOException;

	List<RoomDTO> findAllRooms() throws DAOException;

	void deleteRoom(long hostelId, long roomId) throws DAOException;

	void addRoom(Room room) throws DAOException;

	Room findRoomById(long hostelId, long roomId) throws DAOException;

	void editRoom(Room room) throws DAOException;

	boolean checkRoomId(long hostelId, long roomId) throws DAOException;

	RoomDTO findRoomDTOById(long hostelId, long roomId) throws DAOException;
}
