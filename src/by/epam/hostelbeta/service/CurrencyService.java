package by.epam.hostelbeta.service;

import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.CurrencyDAO;
import by.epam.hostelbeta.domain.entity.Currency;


public class CurrencyService {

	public static List<Currency> getAllCurrency() throws ServiceException {
		CurrencyDAO currencyDAO = new CurrencyDAO();
		try{
			return currencyDAO.findAll();
		}catch(DAOException e){
			throw new ServiceException(e);
		}
	}

}
