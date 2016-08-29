package by.epam.hostelbeta.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.IOrderDAO;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Order;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionDecorator;

public class OrderDAO implements IOrderDAO {
	private static final String SELECT_ORDERS_BY_USER_ID = "SELECT * FROM `v_order_information` WHERE `UserId` = ? ORDER BY `OrderTime` DESC";
	private static final String SELECT_ALL_ORDERS = "SELECT * FROM `v_order_information` ORDER BY `OrderTime` DESC";
	private static final String REJECT_ORDER = "UPDATE `order` SET `Status` = 'Отклонен' WHERE `OrderId` = ?";
	private static final String ACCEPT_ORDER = "UPDATE `order` SET `Status` = 'Принят' WHERE `OrderId` = ?";
	private static final String CANCEL_ORDER = "UPDATE `order` SET `Status` = 'Отказ' WHERE `OrderId` = ?";
	private static final String CHECK_ROOM_AVAILABILITY = "SELECT `OrderId` FROM `order` WHERE `HostelId` = ? AND `RoomId` = ? AND (`InDate` <= ? AND `OutDate` >= ?) AND (`Status` = 'Принят' OR `Status` = 'В обработке')";
	private static final String ADD_ORDER = "INSERT INTO `order` (`UserId`, `HostelId`, `RoomId`, `InDate`, `OutDate`, `Booking`, `Price`) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ORDER_BY_ID = "SELECT * FROM `v_order_information` WHERE `OrderId` = ?";

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
	private static final String USER_LOGIN = "UserLogin";

	public List<OrderDTO> findOrdersByUserId(long userId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		List<OrderDTO> orders = new ArrayList<OrderDTO>();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDERS_BY_USER_ID)) {
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderDTO order = new OrderDTO();
				fillOrderDTO(rs, order);
				orders.add(order);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return orders;
	}

	public List<OrderDTO> findAllOrders() throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		List<OrderDTO> orders = new ArrayList<OrderDTO>();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				OrderDTO order = new OrderDTO();
				fillOrderDTO(rs, order);
				orders.add(order);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return orders;
	}

	public void rejectOrder(long orderId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(REJECT_ORDER)) {
			ps.setLong(1, orderId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public void acceptOrder(long orderId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(ACCEPT_ORDER)) {
			ps.setLong(1, orderId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public void cancelOrder(long orderId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(CANCEL_ORDER)) {
			ps.setLong(1, orderId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public boolean checkRoom(RoomDTO room, LocalDate inDate, LocalDate outDate) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(CHECK_ROOM_AVAILABILITY)) {
			ps.setLong(1, room.getHostelId());
			ps.setLong(2, room.getRoomId());
			ps.setDate(3, java.sql.Date.valueOf(outDate));
			ps.setDate(4, java.sql.Date.valueOf(outDate));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public void bookRoom(Order order) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(ADD_ORDER)) {
			ps.setLong(1, order.getUserId());
			ps.setLong(2, order.getHostelId());
			ps.setLong(3, order.getRoomId());
			ps.setDate(4, java.sql.Date.valueOf(order.getInDate()));
			ps.setDate(5, java.sql.Date.valueOf(order.getOutDate()));
			ps.setInt(6, order.getBooking());
			ps.setDouble(7, order.getPrice());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public OrderDTO findOrderById(long orderId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		OrderDTO order = null;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
			ps.setLong(1, orderId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				order = new OrderDTO();
				fillOrderDTO(rs, order);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return order;
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
		order.setUserLogin(rs.getString(USER_LOGIN));
	}
}
