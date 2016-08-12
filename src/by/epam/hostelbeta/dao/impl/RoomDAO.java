package by.epam.hostelbeta.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.IRoomDAO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionWrapper;

public class RoomDAO implements IRoomDAO {
	private static final String SELECT_ROOMS_BY_HOSTEL_ID = "SELECT `HostelId`,`RoomId`, `RoomType`, `BYNPrice` as `Price`, `BedsNumber` FROM `v_room_information` WHERE `HostelId` = ?";

	private static final String HOSTEL_ID = "HostelId";
	private static final String ROOM_ID = "RoomId";
	private static final String ROOM_TYPE = "RoomType";
	private static final String PRICE = "Price";
	private static final String BEDS_NUMBER = "BedsNumber";

	@Override
	public List<RoomDTO> findRoomsByHostelId(long hostelId) throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_ROOMS_BY_HOSTEL_ID)) {
			ps.setLong(1, hostelId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				RoomDTO room = new RoomDTO();
				fillRoomDTO(rs, room);
				rooms.add(room);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
		return rooms;
	}

	private void fillRoomDTO(ResultSet rs, RoomDTO room) throws SQLException {
		room.setHostelId(rs.getLong(HOSTEL_ID));
		room.setRoomId(rs.getInt(ROOM_ID));
		room.setBedsNumber(rs.getInt(BEDS_NUMBER));
		room.setPrice(rs.getDouble(PRICE));
		room.setRoomType(rs.getString(ROOM_TYPE));
	}
}
