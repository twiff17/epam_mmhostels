package by.epam.hostelbeta.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.OrderDAO;
import by.epam.hostelbeta.dao.impl.RoomDAO;
import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Order;

/**
 * The Class OrderService.
 */
public class OrderService {

	/**
	 * Gets the orders by user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the list of user's orders
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<OrderDTO> getOrdersByUserId(long userId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrdersByUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the all orders.
	 *
	 * @return the list of all orders
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<OrderDTO> getAllOrders() throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findAllOrders();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Reject order.
	 *
	 * @param orderId
	 *            the order id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static void rejectOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.rejectOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Accept order.
	 *
	 * @param orderId
	 *            the order id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static void acceptOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.acceptOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Cancel order.
	 *
	 * @param orderId
	 *            the order id
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static boolean cancelOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			OrderDTO order = orderDAO.findOrderById(orderId);
			if (LocalDate.now().isAfter(order.getInDate()) || LocalDate.now().isEqual(order.getInDate())) {
				return false;
			} else {
				orderDAO.cancelOrder(orderId);
				return true;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Book room.
	 *
	 * @param order
	 *            the order
	 * @param discount
	 *            the discount
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static boolean bookRoom(Order order, boolean discount) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		RoomDAO roomDAO = new RoomDAO();
		try {
			RoomDTO room = roomDAO.findRoomDTOById(order.getHostelId(), order.getRoomId());
			if (!orderDAO.checkRoom(room, order.getInDate(), order.getOutDate())) {
				long period = ChronoUnit.DAYS.between(order.getInDate(), order.getOutDate());
				double price = period * room.getPrice();
				if (discount) {
					price = price * 0.9;
				}
				order.setPrice(price);
				orderDAO.bookRoom(order);
				return true;
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the order by id.
	 *
	 * @param orderId
	 *            the order id
	 * @return the order by id, or null if wasn't found
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static OrderDTO getOrderById(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrderById(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the orders by hostel id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @return the list of orders by hostel id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<OrderDTO> getOrdersByHostelId(long hostelId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrdersByHostelId(hostelId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the orders by room id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @return the list of orders by room id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<OrderDTO> getOrdersByRoomId(long hostelId, long roomId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrdersByRoomId(hostelId, roomId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
