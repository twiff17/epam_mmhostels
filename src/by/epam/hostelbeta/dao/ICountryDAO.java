package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.Country;

public interface ICountryDAO {
	List<Country> findAll() throws DAOException;
}
