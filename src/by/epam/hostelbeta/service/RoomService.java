package by.epam.hostelbeta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.OrderDAO;
import by.epam.hostelbeta.dao.impl.RoomDAO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Room;

public class RoomService {
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

	public static void deleteRoom(long hostelId, long roomId) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		try {
			roomDAO.deleteRoom(hostelId, roomId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

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

	public static Room getRoomById(long hostelId, long roomId) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		try {
			return roomDAO.findRoomById(hostelId, roomId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public static void editRoom(Room room) throws ServiceException {
		RoomDAO roomDAO = new RoomDAO();
		try {
			roomDAO.editRoom(room);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

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
