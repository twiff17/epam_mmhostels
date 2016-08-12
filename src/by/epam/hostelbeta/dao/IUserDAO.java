package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.User;

public interface IUserDAO {
	List<User> findAll() throws DAOException;
	User findByLoginAndPassword(String login, String password) throws DAOException;
	public User insertUser(User user) throws DAOException;
	public boolean checkLogin(String login) throws DAOException;
}
