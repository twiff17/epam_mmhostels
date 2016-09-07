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

public class OrderService {
	public static List<OrderDTO> getOrdersByUserId(long userId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrdersByUserId(userId);
		} catch (DAOException e) {
			throw new ServiceException("OrderService Error getting orders by user id!", e);
		}
	}

	public static List<OrderDTO> getAllOrders() throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findAllOrders();
		} catch (DAOException e) {
			throw new ServiceException("OrderService Error getting all orders!", e);
		}
	}

	public static void rejectOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.rejectOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException("OrderService Error rejecting order!", e);
		}
	}

	public static void acceptOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.acceptOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException("OrderService Error accepting order!", e);
		}
	}

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
			throw new ServiceException("OrderService Error canceling order!", e);
		}
	}

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
			throw new ServiceException("OrderService Error booking room!", e);
		}
	}

	public static OrderDTO getOrderById(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrderById(orderId);
		} catch (DAOException e) {
			throw new ServiceException("OrderService Error getting order by id!", e);
		}
	}

	public static List<OrderDTO> getOrdersByHostelId(long hostelId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrdersByHostelId(hostelId);
		} catch (DAOException e) {
			throw new ServiceException("OrderService Error getting orders by hostel id!", e);
		}
	}

	public static List<OrderDTO> getOrdersByRoomId(long hostelId, long roomId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findOrdersByRoomId(hostelId, roomId);
		} catch (DAOException e) {
			throw new ServiceException("OrderService Error getting orders by room id!", e);
		}
	}
}
