package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.OrderDTO;

public interface IOrderDAO {
	List<OrderDTO> findOrdersByUserId(long userId) throws DAOException;

	List<OrderDTO> findAllOrders() throws DAOException;

	void rejectOrder(long orderId) throws DAOException;

	void acceptOrder(long orderId) throws DAOException;

	void cancelOrder(long orderId) throws DAOException;
}
