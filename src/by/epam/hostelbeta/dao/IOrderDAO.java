package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.OrderDTO;

public interface IOrderDAO {
	List<OrderDTO> findOrdersByUserId(long userId, int offset, int noOfRecords) throws DAOException;

	List<OrderDTO> findAllOrders(int offset, int noOfRecords) throws DAOException;

	void rejectOrder(long orderId) throws DAOException;

	void acceptOrder(long orderId) throws DAOException;

	void cancelOrder(long orderId) throws DAOException;
}
