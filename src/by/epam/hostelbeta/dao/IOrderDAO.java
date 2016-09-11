package by.epam.hostelbeta.dao;

import java.time.LocalDate;
import java.util.List;

import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Order;

// TODO: Auto-generated Javadoc
/**
 * The Interface IOrderDAO.
 */
public interface IOrderDAO {
	
	/**
	 * Find orders by user id.
	 *
	 * @param userId the user id
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<OrderDTO> findOrdersByUserId(long userId) throws DAOException;

	/**
	 * Find all orders.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<OrderDTO> findAllOrders() throws DAOException;

	/**
	 * Reject order.
	 *
	 * @param orderId the order id
	 * @throws DAOException the DAO exception
	 */
	void rejectOrder(long orderId) throws DAOException;

	/**
	 * Accept order.
	 *
	 * @param orderId the order id
	 * @throws DAOException the DAO exception
	 */
	void acceptOrder(long orderId) throws DAOException;

	/**
	 * Cancel order.
	 *
	 * @param orderId the order id
	 * @throws DAOException the DAO exception
	 */
	void cancelOrder(long orderId) throws DAOException;

	/**
	 * Check room.
	 *
	 * @param room the room
	 * @param inDate the in date
	 * @param outDate the out date
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	boolean checkRoom(RoomDTO room, LocalDate inDate, LocalDate outDate) throws DAOException;

	/**
	 * Book room.
	 *
	 * @param order the order
	 * @throws DAOException the DAO exception
	 */
	void bookRoom(Order order) throws DAOException;

	/**
	 * Find order by id.
	 *
	 * @param orderId the order id
	 * @return the order DTO
	 * @throws DAOException the DAO exception
	 */
	OrderDTO findOrderById(long orderId) throws DAOException;

	/**
	 * Find orders by hostel id.
	 *
	 * @param hostelId the hostel id
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<OrderDTO> findOrdersByHostelId(long hostelId) throws DAOException;

	/**
	 * Find orders by room id.
	 *
	 * @param hostelId the hostel id
	 * @param roomId the room id
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<OrderDTO> findOrdersByRoomId(long hostelId, long roomId) throws DAOException;
}
