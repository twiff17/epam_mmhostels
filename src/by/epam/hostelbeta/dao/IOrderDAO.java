package by.epam.hostelbeta.dao;

import java.time.LocalDate;
import java.util.List;

import by.epam.hostelbeta.domain.dto.OrderDTO;
import by.epam.hostelbeta.domain.dto.RoomDTO;
import by.epam.hostelbeta.domain.entity.Order;

/**
 * The Interface IOrderDAO.
 */
public interface IOrderDAO {

	/**
	 * Find orders by user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the list of user's orders
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<OrderDTO> findOrdersByUserId(long userId) throws DAOException;

	/**
	 * Find all orders.
	 *
	 * @return the list of all orders
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<OrderDTO> findAllOrders() throws DAOException;

	/**
	 * Reject order.
	 *
	 * @param orderId
	 *            the order id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void rejectOrder(long orderId) throws DAOException;

	/**
	 * Accept order.
	 *
	 * @param orderId
	 *            the order id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void acceptOrder(long orderId) throws DAOException;

	/**
	 * Cancel order.
	 *
	 * @param orderId
	 *            the order id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void cancelOrder(long orderId) throws DAOException;

	/**
	 * Check room's availability.
	 *
	 * @param room
	 *            the room
	 * @param inDate
	 *            the in date
	 * @param outDate
	 *            the out date
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	boolean checkRoom(RoomDTO room, LocalDate inDate, LocalDate outDate) throws DAOException;

	/**
	 * Book room.
	 *
	 * @param order
	 *            the order
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void bookRoom(Order order) throws DAOException;

	/**
	 * Find order by id.
	 *
	 * @param orderId
	 *            the order id
	 * @return the order DTO or null if order wasn't found
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	OrderDTO findOrderById(long orderId) throws DAOException;

	/**
	 * Find orders by hostel id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @return the list of orders
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<OrderDTO> findOrdersByHostelId(long hostelId) throws DAOException;

	/**
	 * Find orders by room id.
	 *
	 * @param hostelId
	 *            the hostel id
	 * @param roomId
	 *            the room id
	 * @return the list or orders
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<OrderDTO> findOrdersByRoomId(long hostelId, long roomId) throws DAOException;
}
