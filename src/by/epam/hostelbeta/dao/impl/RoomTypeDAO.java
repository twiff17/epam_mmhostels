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

/**
 * The Class RoomTypeDAO. Class to access the data about room types
 */
public class RoomTypeDAO {

	/** The Constant SELECT_ALL. */
	private static final String SELECT_ALL = "SELECT * FROM `room_type`";

	/** The Constant NAME. */
	private static final String NAME = "Name";

	/** The Constant COEFF. */
	private static final String COEFF = "Coeff";

	/** The Constant ROOM_TYPE_ID. */
	private static final String ROOM_TYPE_ID = "RoomTypeId";

	/**
	 * Find all room types.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
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
			throw new DAOException(e);
		} finally {
			connection.close();
		}
		return types;
	}

	/**
	 * Fill type.
	 *
	 * @param rs
	 *            the ResultSet with data
	 * @param roomType
	 *            the room type
	 * @throws SQLException
	 *             the SQL exception
	 */
	private void fillType(ResultSet rs, RoomType roomType) throws SQLException {
		roomType.setCoeff(rs.getDouble(COEFF));
		roomType.setName(rs.getString(NAME));
		roomType.setRoomTypeId(rs.getInt(ROOM_TYPE_ID));

	}

}
