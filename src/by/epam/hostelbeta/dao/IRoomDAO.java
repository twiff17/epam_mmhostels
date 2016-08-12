package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.RoomDTO;

public interface IRoomDAO {
	public List<RoomDTO> findRoomsByHostelId(long hostelId) throws DAOException;
}
