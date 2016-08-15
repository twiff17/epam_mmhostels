package by.epam.hostelbeta.service;

import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.OrderDAO;
import by.epam.hostelbeta.domain.dto.OrderDTO;

public class OrderService {
	private static final int RECORDS_PER_PAGE = 10;

	public static int getOrdersByUserId(long userId, int pageNumber, List<OrderDTO> orders) throws ServiceException {
		OrderDAO orderDAO = new OrderDAO();
		try{
			orders.addAll(orderDAO.findOrdersByUserId(userId, (pageNumber - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE));
			int noOfRecords = orderDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
			return noOfPages;
		}catch(DAOException e){
			throw new ServiceException(e);
		}
	}
	
	public static int getAllOrders(int pageNumber, List<OrderDTO> orders) throws ServiceException{
		OrderDAO orderDAO = new OrderDAO();
		try{
			orders.addAll(orderDAO.findAllOrders((pageNumber - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE));
			int noOfRecords = orderDAO.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);
			return noOfPages;
		}catch(DAOException e){
			throw new ServiceException(e);
		}
	}
	
	public static void rejectOrder(long orderId) throws ServiceException{
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.rejectOrder(orderId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
