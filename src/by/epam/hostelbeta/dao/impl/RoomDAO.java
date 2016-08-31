package by.epam.hostelbeta.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.IRoomDAO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Room;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionDecorator;

public class RoomDAO implements IRoomDAO {
	private static final String SELECT_ROOMS_BY_HOSTEL_ID = "SELECT `HostelId`, `RoomId`, `Name` as `HostelName`, `RoomType`, `BYNPrice` as `Price`, `BedsNumber` FROM `v_room_information` WHERE `HostelId` = ?";
	private static final String SELECT_ALL_ROOMS = "SELECT `HostelId`, `RoomId`, `Name` as `HostelName`, `RoomType`, `BYNPrice` as `Price`, `BedsNumber` FROM `v_room_information`";
	private static final String DELETE_ROOM = "DELETE FROM `room` WHERE `HostelId` = ? AND `RoomId` = ?";
	private static final String ADD_ROOM = "INSERT INTO `room` (`HostelId`, `RoomId`, `RoomType`, `BedsNumber`) VALUES(?, ?, ?, ?)";
	private static final String SELECT_ROOM_BY_ID = "SELECT * FROM `room` WHERE `HostelId` = ? AND `RoomId` = ?";
	private static final String EDIT_ROOM = "UPDATE `room` SET `RoomType` = ?, `BedsNumber` = ? WHERE `HostelId` = ? AND `RoomId` = ?";
	private static final String SELECT_ROOM_DTO_BY_ID = "SELECT `HostelId`, `RoomId`, `Name` as `HostelName`, `RoomType`, `BYNPrice` as `Price`, `BedsNumber` FROM `v_room_information` WHERE `HostelId` = ? AND `RoomId` = ?";

	private static final String HOSTEL_ID = "HostelId";
	private static final String ROOM_ID = "RoomId";
	private static final String ROOM_TYPE = "RoomType";
	private static final String PRICE = "Price";
	private static final String BEDS_NUMBER = "BedsNumber";
	private static final String HOSTEL_NAME = "HostelName";

	@Override
	public List<RoomDTO> findRoomsByHostelId(long hostelId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
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

	public List<RoomDTO> findAllRooms() throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		List<RoomDTO> rooms = new ArrayList<RoomDTO>();

		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(SELECT_ALL_ROOMS);

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

	public void deleteHostel(long hostelId, long roomId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(DELETE_ROOM)) {
			ps.setLong(1, hostelId);
			ps.setLong(2, roomId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public void addRoom(Room room) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(ADD_ROOM)) {
			ps.setLong(1, room.getHostelId());
			ps.setLong(2, room.getRoomId());
			ps.setInt(3, room.getRoomType());
			ps.setInt(4, room.getBedsNumber());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public Room findRoomById(long hostelId, long roomId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		Room room = null;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ROOM_BY_ID)) {
			ps.setLong(1, hostelId);
			ps.setLong(2, roomId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				room = new Room();
				fillRoom(rs, room);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
		return room;
	}

	public void editRoom(Room room) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(EDIT_ROOM)) {
			ps.setInt(1, room.getRoomType());
			ps.setInt(2, room.getBedsNumber());
			ps.setLong(3, room.getHostelId());
			ps.setLong(4, room.getRoomId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public boolean checkRoomId(long hostelId, long roomId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ROOM_BY_ID)) {
			ps.setLong(1, hostelId);
			ps.setLong(2, roomId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
		return false;
	}

	public RoomDTO findRoomDTOById(long hostelId, long roomId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		RoomDTO room = null;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ROOM_DTO_BY_ID)) {
			ps.setLong(1, hostelId);
			ps.setLong(2, roomId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				room = new RoomDTO();
				fillRoomDTO(rs, room);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
		return room;
	}

	private void fillRoomDTO(ResultSet rs, RoomDTO room) throws SQLException {
		room.setHostelName(rs.getString(HOSTEL_NAME));
		room.setHostelId(rs.getLong(HOSTEL_ID));
		room.setRoomId(rs.getInt(ROOM_ID));
		room.setBedsNumber(rs.getInt(BEDS_NUMBER));
		room.setPrice(rs.getDouble(PRICE));
		room.setRoomType(rs.getString(ROOM_TYPE));
	}

	private void fillRoom(ResultSet rs, Room room) throws SQLException {
		room.setBedsNumber(rs.getInt(BEDS_NUMBER));
		room.setHostelId(rs.getLong(HOSTEL_ID));
		room.setRoomId(rs.getLong(ROOM_ID));
		room.setRoomType(rs.getInt(ROOM_TYPE));
	}
}
