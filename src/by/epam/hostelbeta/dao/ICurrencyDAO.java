package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.domain.entity.CurrencyRate;

public interface ICurrencyDAO {
	List<Currency> findAll() throws DAOException;

	void insertCurrencyRate(CurrencyRate rate) throws DAOException;
}
