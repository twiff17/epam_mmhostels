package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.Currency;

public interface ICurrencyDAO {
	List<Currency> findAll() throws DAOException;
}
