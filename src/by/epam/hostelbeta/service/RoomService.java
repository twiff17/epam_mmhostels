package by.epam.hostelbeta.service;

import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.RoomDAO;
import by.epam.hostelbeta.domain.dto.RoomDTO;

public class RoomService {
	public static List<RoomDTO> getRoomsByHostelId(long hostelId) throws ServiceException{
		RoomDAO roomDAO = new RoomDAO();
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();
		try{
			rooms = roomDAO.findRoomsByHostelId(hostelId);
		}catch(DAOException e){
			throw new ServiceException(e);
		}
		return rooms;
	}
}
