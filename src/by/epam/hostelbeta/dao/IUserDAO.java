package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.User;

/**
 * The Interface IUserDAO.
 */
public interface IUserDAO {

	/**
	 * Find all users.
	 *
	 * @return the list of all users
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	List<User> findAll() throws DAOException;

	/**
	 * Find user by login and password.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @return the user, or null if user wasn't found
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	User findByLoginAndPassword(String login, String password) throws DAOException;

	/**
	 * Insert user.
	 *
	 * @param user
	 *            the user
	 * @return the user
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	public User insertUser(User user) throws DAOException;

	/**
	 * Check login availability.
	 *
	 * @param login
	 *            the login
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	public boolean checkLogin(String login) throws DAOException;

	/**
	 * Ban user.
	 *
	 * @param userId
	 *            the user id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void banUser(long userId) throws DAOException;

	/**
	 * Unban user.
	 *
	 * @param userId
	 *            the user id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void unbanUser(long userId) throws DAOException;

	/**
	 * Adds the discount.
	 *
	 * @param userId
	 *            the user id
	 * @throws DAOException
	 *             the DAO exception, throws when SQLException is thrown
	 */
	void addDiscount(long userId) throws DAOException;
}
