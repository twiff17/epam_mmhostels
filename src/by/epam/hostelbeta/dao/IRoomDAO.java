package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Room;

/**
 * The Interface IRoomDAO.
 */
public interface IRoomDAO {

	/**
	 * Find rooms by hostel id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @return the list of rooms
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	public List<RoomDTO> findRoomsByHostelId(long hostelId) throws DAOException;

	/**
	 * Find all rooms.
	 *
	 * @return the list of all rooms
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<RoomDTO> findAllRooms() throws DAOException;

	/**
	 * Delete room.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void deleteRoom(long hostelId, long roomId) throws DAOException;

	/**
	 * Adds the room.
	 *
	 * @param room
	 *            the room
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void addRoom(Room room) throws DAOException;

	/**
	 * Find room by id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @return the room or null if room wasn't found
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	Room findRoomById(long hostelId, long roomId) throws DAOException;

	/**
	 * Edits the room.
	 *
	 * @param room
	 *            the room
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void editRoom(Room room) throws DAOException;

	/**
	 * Check room id availability.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	boolean checkRoomId(long hostelId, long roomId) throws DAOException;

	/**
	 * Find room DTO by id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @return the room DTO or null if room wasn't found
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	RoomDTO findRoomDTOById(long hostelId, long roomId) throws DAOException;
}
