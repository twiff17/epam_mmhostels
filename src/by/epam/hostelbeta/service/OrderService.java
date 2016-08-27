package by.epam.hostelbeta.service;

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
			throw new ServiceException(e);
		}
	}

	public static List<OrderDTO> getAllOrders() throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			return orderDAO.findAllOrders();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public static void rejectOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.rejectOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public static void acceptOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.acceptOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public static void cancelOrder(long orderId) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.cancelOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public static boolean bookRoom(Order order) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		RoomDAO roomDAO = new RoomDAO();
		try {
			RoomDTO room = roomDAO.findRoomDTOById(order.getHostelId(), order.getRoomId());
			if (!orderDAO.checkRoom(room, order.getInDate(), order.getOutDate())) {
				long period = ChronoUnit.DAYS.between(order.getInDate(), order.getOutDate());
				double price = period * room.getPrice();
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
}
