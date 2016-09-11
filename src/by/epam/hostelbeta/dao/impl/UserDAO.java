package by.epam.hostelbeta.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.IUserDAO;
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionDecorator;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
public class UserDAO implements IUserDAO {
	
	/** The Constant SELECT_ALL_USERS. */
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";
	
	/** The Constant SELECT_USER_BY_LOGIN_PASSWORD. */
	private static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM `user` WHERE Login = ? AND Password = ?";
	
	/** The Constant INSERT_USER. */
	private static final String INSERT_USER = "INSERT INTO `user`(Login, Password, Fullname, Passport, Email, Phone) VALUES(?, ?, ?, ?, ?, ?)";
	
	/** The Constant SELECT_USER_BY_LOGIN. */
	private static final String SELECT_USER_BY_LOGIN = "SELECT `UserId` FROM `user` WHERE Login = ?";
	
	/** The Constant BAN_USER. */
	private static final String BAN_USER = "UPDATE `user` SET `Ban` = 1 WHERE `UserId` = ?";
	
	/** The Constant UNBAN_USER. */
	private static final String UNBAN_USER = "UPDATE `user` SET `Ban` = 0 WHERE `UserId` = ?";
	
	/** The Constant ADD_DISCOUNT. */
	private static final String ADD_DISCOUNT = "UPDATE `user` SET `Discount` = 1 WHERE `UserId` = ?";

	/** The Constant USER_ID. */
	private static final String USER_ID = "UserId";
	
	/** The Constant LOGIN. */
	private static final String LOGIN = "Login";
	
	/** The Constant ROLE. */
	private static final String ROLE = "Role";
	
	/** The Constant FULLNAME. */
	private static final String FULLNAME = "Fullname";
	
	/** The Constant PASSPORT. */
	private static final String PASSPORT = "Passport";
	
	/** The Constant EMAIL. */
	private static final String EMAIL = "Email";
	
	/** The Constant PHONE. */
	private static final String PHONE = "Phone";
	
	/** The Constant BAN. */
	private static final String BAN = "Ban";
	
	/** The Constant DISCOUNT. */
	private static final String DISCOUNT = "Discount";

	/** The Constant ROLE_CLIENT. */
	private static final String ROLE_CLIENT = "client";
	
	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "admin";

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.dao.IUserDAO#findAll()
	 */
	public List<User> findAll() throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		List<User> users = new ArrayList<User>();

		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(SELECT_ALL_USERS);

			while (rs.next()) {
				User user = new User();
				fillUser(rs, user);
				users.add(user);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return users;
	}

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.dao.IUserDAO#findByLoginAndPassword(java.lang.String, java.lang.String)
	 */
	public User findByLoginAndPassword(String login, String password) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		User user = null;

		try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_PASSWORD)) {
			ps.setString(1, login);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				fillUser(rs, user);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return user;
	}

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.dao.IUserDAO#insertUser(by.epam.hostelbeta.domain.entity.User)
	 */
	public User insertUser(User user) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getPassport());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhone());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				user.setUserId(rs.getLong(1));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return user;
	}

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.dao.IUserDAO#checkLogin(java.lang.String)
	 */
	public boolean checkLogin(String login) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
			ps.setString(1, login);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.dao.IUserDAO#banUser(long)
	 */
	public void banUser(long userId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(BAN_USER)) {
			ps.setLong(1, userId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.dao.IUserDAO#unbanUser(long)
	 */
	public void unbanUser(long userId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(UNBAN_USER)) {
			ps.setLong(1, userId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.dao.IUserDAO#addDiscount(long)
	 */
	public void addDiscount(long userId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();

		try (PreparedStatement ps = connection.prepareStatement(ADD_DISCOUNT)) {
			ps.setLong(1, userId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	/**
	 * Fill user.
	 *
	 * @param rs the rs
	 * @param user the user
	 * @throws SQLException the SQL exception
	 */
	private void fillUser(ResultSet rs, User user) throws SQLException {
		user.setUserId(rs.getLong(USER_ID));
		user.setFullname(rs.getString(FULLNAME));
		user.setPassport(rs.getString(PASSPORT));
		user.setEmail(rs.getString(EMAIL));
		user.setPhone(rs.getString(PHONE));
		user.setLogin(rs.getString(LOGIN));
		user.setBan(rs.getBoolean(BAN));
		user.setDiscount(rs.getBoolean(DISCOUNT));
		boolean role = rs.getBoolean(ROLE);
		if (!role) {
			user.setRole(ROLE_CLIENT);
		} else {
			user.setRole(ROLE_ADMIN);
		}
	}
}
