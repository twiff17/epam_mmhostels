package by.epam.hostelbeta.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.UserDAO;
import by.epam.hostelbeta.domain.entity.User;

public class UserService {
	public static User checkLoginPassword(String enterLogin, String enterPassword) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		User user = null;
		String encryptedPassword = DigestUtils.md5Hex(enterPassword);
		try {
			user = userDAO.findByLoginAndPassword(enterLogin, encryptedPassword);
		} catch (DAOException e) {
			throw new ServiceException("UserService error checking login and password!", e);
		}
		return user;
	}

	public static boolean checkLogin(String login) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		boolean isLoginExists = false;
		try {
			isLoginExists = userDAO.checkLogin(login);
		} catch (DAOException e) {
			throw new ServiceException("UserService error checking login availability!", e);
		}
		return isLoginExists;
	}

	public static User signUp(User user) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			if (!userDAO.checkLogin(user.getLogin())) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
				return userDAO.insertUser(user);
			} else {
				return null;
			}
		} catch (DAOException e) {
			throw new ServiceException("UserService error signing up!", e);
		}
	}

	public static List<User> getAllUsers() throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			return userDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException("UserService error getting all users!", e);
		}
	}

	public static void banUser(long userId) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.banUser(userId);
		} catch (DAOException e) {
			throw new ServiceException("UserService error banning user!", e);
		}
	}

	public static void unbanUser(long userId) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.unbanUser(userId);
		} catch (DAOException e) {
			throw new ServiceException("UserService error unbanning!", e);
		}
	}

	public static void addDiscount(long userId) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.addDiscount(userId);
		} catch (DAOException e) {
			throw new ServiceException("UserService error adding discount!", e);
		}
	}
}
