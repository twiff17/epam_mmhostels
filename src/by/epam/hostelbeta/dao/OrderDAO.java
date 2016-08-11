package by.epam.hostelbeta.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionWrapper;

public class OrderDAO {
	private static final String SELECT_ORDERS_BY_USER_ID = "SELECT * FROM `v_order_information` WHERE `UserId` = ? LIMIT ?, ?";

	private static final String USER_ID = "UserId";
	private static final String FULLNAME = "Fullname";
	private static final String PASSPORT = "Passport";
	private static final String EMAIL = "Email";
	private static final String PHONE = "Phone";
	private static final String HOSTEL_NAME = "HostelName";
	private static final String COUNTRY = "Country";
	private static final String CITY = "City";
	private static final String ROOM_TYPE = "RoomType";
	private static final String ORDER_TIME = "OrderTime";
	private static final String STATUS = "Status";
	private static final String IN_DATE = "InDate";
	private static final String OUT_DATE = "OutDate";
	private static final String BOOKING = "Booking";
	private static final String PRICE = "Price";
	private static final String BYN_PRICE = "BYNPrice";
	private static final String CURRENCY = "Currency";
	private static final String ROOM_ID = "RoomId";

	private int noOfRecords;

	public List<OrderDTO> findOrdersByUserId(long userId, int offset, int noOfRecords) throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		List<OrderDTO> orders = new ArrayList<OrderDTO>();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID)) {
			ps.setLong(1, userId);
			ps.setInt(2, offset);
			ps.setInt(3, noOfRecords);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				OrderDTO order = new OrderDTO();
				fillOrderDTO(rs, order);
				orders.add(order);
			}
			
			rs = ps.executeQuery("SELECT FOUND_ROWS()");
			if (rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return orders;
	}

	private void fillOrderDTO(ResultSet rs, OrderDTO order) throws SQLException {
		order.setUserId(rs.getLong(USER_ID));
		order.setBynPrice(rs.getDouble(BYN_PRICE));
		order.setBooking(rs.getBoolean(BOOKING));
		order.setCountry(rs.getString(COUNTRY));
		order.setCity(rs.getString(CITY));
		order.setCurrency(rs.getString(CURRENCY));
		order.setEmail(rs.getString(EMAIL));
		order.setFullname(rs.getString(FULLNAME));
		order.setHostelName(rs.getString(HOSTEL_NAME));
		order.setInDate(rs.getDate(IN_DATE).toLocalDate());
		order.setOrderTime(rs.getTimestamp(ORDER_TIME).toLocalDateTime());
		order.setOutDate(rs.getDate(OUT_DATE).toLocalDate());
		order.setPassport(rs.getString(PASSPORT));
		order.setPhone(rs.getString(PHONE));
		order.setPrice(rs.getDouble(PRICE));
		order.setRoomId(rs.getInt(ROOM_ID));
		order.setRoomType(rs.getString(ROOM_TYPE));
		order.setStatus(rs.getString(STATUS));
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
}
