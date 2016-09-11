package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Room;

// TODO: Auto-generated Javadoc
/**
 * The Interface IRoomDAO.
 */
public interface IRoomDAO {
	
	/**
	 * Find rooms by hostel id.
	 *
	 * @param hostelId the hostel id
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	public List<RoomDTO> findRoomsByHostelId(long hostelId) throws DAOException;

	/**
	 * Find all rooms.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<RoomDTO> findAllRooms() throws DAOException;

	/**
	 * Delete room.
	 *
	 * @param hostelId the hostel id
	 * @param roomId the room id
	 * @throws DAOException the DAO exception
	 */
	void deleteRoom(long hostelId, long roomId) throws DAOException;

	/**
	 * Adds the room.
	 *
	 * @param room the room
	 * @throws DAOException the DAO exception
	 */
	void addRoom(Room room) throws DAOException;

	/**
	 * Find room by id.
	 *
	 * @param hostelId the hostel id
	 * @param roomId the room id
	 * @return the room
	 * @throws DAOException the DAO exception
	 */
	Room findRoomById(long hostelId, long roomId) throws DAOException;

	/**
	 * Edits the room.
	 *
	 * @param room the room
	 * @throws DAOException the DAO exception
	 */
	void editRoom(Room room) throws DAOException;

	/**
	 * Check room id.
	 *
	 * @param hostelId the hostel id
	 * @param roomId the room id
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	boolean checkRoomId(long hostelId, long roomId) throws DAOException;

	/**
	 * Find room DTO by id.
	 *
	 * @param hostelId the hostel id
	 * @param roomId the room id
	 * @return the room DTO
	 * @throws DAOException the DAO exception
	 */
	RoomDTO findRoomDTOById(long hostelId, long roomId) throws DAOException;
}
