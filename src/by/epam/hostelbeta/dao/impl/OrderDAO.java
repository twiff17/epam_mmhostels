package by.epam.hostelbeta.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.IOrderDAO;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionWrapper;

public class OrderDAO implements IOrderDAO {
	private static final String SELECT_ORDERS_BY_USER_ID = "SELECT * FROM `v_order_information` WHERE `UserId` = ? LIMIT ?, ?";
	private static final String SELECT_ALL_ORDERS = "SELECT * FROM `v_order_information` ORDER BY `OrderTime` DESC LIMIT ?, ?";
	private static final String REJECT_ORDER = "UPDATE `order` SET `Status` = '��������' WHERE `OrderId` = ?";
	private static final String ACCEPT_ORDER = "UPDATE `order` SET `Status` = '������' WHERE `OrderId` = ?";
	private static final String CANCEL_ORDER = "UPDATE `order` SET `Status` = '�����' WHERE `OrderId` = ?";
	
	private static final String USER_ID = "UserId";
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
	private static final String ROOM_ID = "RoomId";
	private static final String ORDER_ID = "OrderId";
	

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

	public List<OrderDTO> findAllOrders(int offset, int noOfRecords) throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		List<OrderDTO> orders = new ArrayList<OrderDTO>();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS)) {
			ps.setInt(1, offset);
			ps.setInt(2, noOfRecords);
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

	public boolean rejectOrder(long orderId) throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(REJECT_ORDER)) {
			ps.setLong(1, orderId);
			int result = ps.executeUpdate();

			if (result > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean acceptOrder(long orderId) throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(ACCEPT_ORDER)) {
			ps.setLong(1, orderId);
			int result = ps.executeUpdate();

			if (result > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public boolean cancelOrder(long orderId) throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(CANCEL_ORDER)) {
			ps.setLong(1, orderId);
			int result = ps.executeUpdate();

			if (result > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	private void fillOrderDTO(ResultSet rs, OrderDTO order) throws SQLException {
		order.setOrderId(rs.getLong(ORDER_ID));
		order.setUserId(rs.getLong(USER_ID));
		order.setBooking(rs.getBoolean(BOOKING));
		order.setCountry(rs.getString(COUNTRY));
		order.setCity(rs.getString(CITY));
		order.setHostelName(rs.getString(HOSTEL_NAME));
		order.setInDate(rs.getDate(IN_DATE).toLocalDate());
		order.setOrderTime(rs.getTimestamp(ORDER_TIME).toLocalDateTime());
		order.setOutDate(rs.getDate(OUT_DATE).toLocalDate());
		order.setPrice(rs.getDouble(PRICE));
		order.setRoomId(rs.getInt(ROOM_ID));
		order.setRoomType(rs.getString(ROOM_TYPE));
		order.setStatus(rs.getString(STATUS));
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
}