package by.epam.hostelbeta.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.UserDAO;
import by.epam.hostelbeta.domain.entity.User;

/**
 * The Class UserService.
 */
public class UserService {

	/**
	 * Check login password.
	 *
	 * @param enteredLogin
	 *            the entered login
	 * @param enteredPassword
	 *            the entered password
	 * @return the user, or null if wasn't found
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static User checkLoginPassword(String enteredLogin, String enteredPassword) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		User user = null;
		String encryptedPassword = DigestUtils.md5Hex(enteredPassword);
		try {
			user = userDAO.findByLoginAndPassword(enteredLogin, encryptedPassword);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	/**
	 * Check login availability.
	 *
	 * @param login
	 *            the login
	 * @return true, if successful
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static boolean checkLogin(String login) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		boolean isLoginExists = false;
		try {
			isLoginExists = userDAO.checkLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return isLoginExists;
	}

	/**
	 * Sign up.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
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
			throw new ServiceException(e);
		}
	}

	/**
	 * Gets the all users.
	 *
	 * @return the list of all users
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static List<User> getAllUsers() throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			return userDAO.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Ban user.
	 *
	 * @param userId
	 *            the user id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static void banUser(long userId) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.banUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Unban user.
	 *
	 * @param userId
	 *            the user id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static void unbanUser(long userId) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.unbanUser(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Adds the discount.
	 *
	 * @param userId
	 *            the user id
	 * @throws ServiceException
	 *             the service exception, if DAOException was thrown
	 */
	public static void addDiscount(long userId) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.addDiscount(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
