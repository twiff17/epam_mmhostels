package by.epam.hostelbeta.dao;

import java.time.LocalDate;
import java.util.List;

import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Order;

public interface IOrderDAO {
	List<OrderDTO> findOrdersByUserId(long userId) throws DAOException;

	List<OrderDTO> findAllOrders() throws DAOException;

	void rejectOrder(long orderId) throws DAOException;

	void acceptOrder(long orderId) throws DAOException;

	void cancelOrder(long orderId) throws DAOException;

	boolean checkRoom(RoomDTO room, LocalDate inDate, LocalDate outDate) throws DAOException;

	void bookRoom(Order order) throws DAOException;

	OrderDTO findOrderById(long orderId) throws DAOException;

	List<OrderDTO> findOrdersByHostelId(long hostelId) throws DAOException;

	List<OrderDTO> findOrdersByRoomId(long hostelId, long roomId) throws DAOException;
}
