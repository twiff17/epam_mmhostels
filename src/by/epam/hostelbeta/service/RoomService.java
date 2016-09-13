package by.epam.hostelbeta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.OrderDAO;
import by.epam.hostelbeta.dao.impl.RoomDAO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Room;

/**
 * The Class RoomService.
 */
public class RoomService {

	/**
	 * Gets the rooms by hostel id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @return the list of rooms in hostel
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<RoomDTO> getRoomsByHostelId(long hostelId) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();
		try {
			rooms = roomDAO.findRoomsByHostelId(hostelId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return rooms;
	}

	/**
	 * Gets the all rooms.
	 *
	 * @return the list of all rooms
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<RoomDTO> getAllRooms() throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();
		try {
			rooms = roomDAO.findAllRooms();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return rooms;
	}

	/**
	 * Delete room.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static void deleteRoom(long hostelId, long roomId) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		try {
			roomDAO.deleteRoom(hostelId, roomId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Adds the room.
	 *
	 * @param room
	 *            the room
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static boolean addRoom(Room room) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		try {
			if (!roomDAO.checkRoomId(room.getHostelId(), room.getRoomId())) {
				roomDAO.addRoom(room);
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the room by id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @return the room by id, or null if wasn't found
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static Room getRoomById(long hostelId, long roomId) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		try {
			return roomDAO.findRoomById(hostelId, roomId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Edits the room.
	 *
	 * @param room
	 *            the room
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static void editRoom(Room room) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		try {
			roomDAO.editRoom(room);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the free rooms for a given date.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param inDate
	 *            the in date
	 * @param outDate
	 *            the out date
	 * @return the list of free rooms
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<RoomDTO> getFreeRooms(long hostelId, LocalDate inDate, LocalDate outDate)
			throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		OrderDAO orderDAO = new OrderDAO();
		List<RoomDTO> allRooms = new ArrayList<RoomDTO>();
		List<RoomDTO> freeRooms = new ArrayList<RoomDTO>();
		try {
			allRooms = roomDAO.findRoomsByHostelId(hostelId);
			for (RoomDTO room : allRooms) {
				if (!orderDAO.checkRoom(room, inDate, outDate)) {
					freeRooms.add(room);
				}
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return freeRooms;
	}
}
