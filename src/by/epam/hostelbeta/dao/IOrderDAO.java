package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.dto.OrderDTO;

public interface IOrderDAO {
	List<OrderDTO> findOrdersByUserId(long userId, int offset, int noOfRecords) throws DAOException;
}
