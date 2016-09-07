package by.epam.hostelbeta.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.domain.entity.RoomType;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionDecorator;

public class RoomTypeDAO {
	private static final String SELECT_ALL = "SELECT * FROM `room_type`";

	private static final String NAME = "Name";
	private static final String COEFF = "Coeff";
	private static final String ROOM_TYPE_ID = "RoomTypeId";

	public List<RoomType> findAllRoomTypes() throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		List<RoomType> types = new ArrayList<RoomType>();
		try (Statement st = connection.createStatement()) {

			ResultSet rs = st.executeQuery(SELECT_ALL);

			while (rs.next()) {
				RoomType roomType = new RoomType();
				fillType(rs, roomType);
				types.add(roomType);
			}

		} catch (SQLException e) {
			throw new DAOException("RoomTypeDAO Error finding all room types!", e);
		} finally {
			connection.close();
		}
		return types;
	}

	private void fillType(ResultSet rs, RoomType roomType) throws SQLException {
		roomType.setCoeff(rs.getDouble(COEFF));
		roomType.setName(rs.getString(NAME));
		roomType.setRoomTypeId(rs.getInt(ROOM_TYPE_ID));

	}

}
