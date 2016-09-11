package by.epam.hostelbeta.dao;

import java.util.List;

import by.epam.hostelbeta.domain.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface IUserDAO.
 */
public interface IUserDAO {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<User> findAll() throws DAOException;

	/**
	 * Find by login and password.
	 *
	 * @param login the login
	 * @param password the password
	 * @return the user
	 * @throws DAOException the DAO exception
	 */
	User findByLoginAndPassword(String login, String password) throws DAOException;

	/**
	 * Insert user.
	 *
	 * @param user the user
	 * @return the user
	 * @throws DAOException the DAO exception
	 */
	public User insertUser(User user) throws DAOException;

	/**
	 * Check login.
	 *
	 * @param login the login
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	public boolean checkLogin(String login) throws DAOException;

	/**
	 * Ban user.
	 *
	 * @param userId the user id
	 * @throws DAOException the DAO exception
	 */
	void banUser(long userId) throws DAOException;

	/**
	 * Unban user.
	 *
	 * @param userId the user id
	 * @throws DAOException the DAO exception
	 */
	void unbanUser(long userId) throws DAOException;

	/**
	 * Adds the discount.
	 *
	 * @param userId the user id
	 * @throws DAOException the DAO exception
	 */
	void addDiscount(long userId) throws DAOException;
}
