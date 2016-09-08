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

public class UserDAO implements IUserDAO {
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";
	private static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM `user` WHERE Login = ? AND Password = ?";
	private static final String INSERT_USER = "INSERT INTO `user`(Login, Password, Fullname, Passport, Email, Phone) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SELECT_USER_BY_LOGIN = "SELECT `UserId` FROM `user` WHERE Login = ?";
	private static final String BAN_USER = "UPDATE `user` SET `Ban` = 1 WHERE `UserId` = ?";
	private static final String UNBAN_USER = "UPDATE `user` SET `Ban` = 0 WHERE `UserId` = ?";
	private static final String ADD_DISCOUNT = "UPDATE `user` SET `Discount` = 1 WHERE `UserId` = ?";

	private static final String USER_ID = "UserId";
	private static final String LOGIN = "Login";
	private static final String ROLE = "Role";
	private static final String FULLNAME = "Fullname";
	private static final String PASSPORT = "Passport";
	private static final String EMAIL = "Email";
	private static final String PHONE = "Phone";
	private static final String BAN = "Ban";
	private static final String DISCOUNT = "Discount";

	private static final String ROLE_CLIENT = "client";
	private static final String ROLE_ADMIN = "admin";

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
