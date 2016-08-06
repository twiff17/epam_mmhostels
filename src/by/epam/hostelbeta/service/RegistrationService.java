package by.epam.hostelbeta.service;

import org.apache.commons.codec.digest.DigestUtils;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.UserDAO;
import by.epam.hostelbeta.entity.User;

public class RegistrationService {
	public static boolean signUp(User user) throws ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			if (!userDAO.checkLogin(user.getLogin())) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
				return userDAO.insertUser(user);
			} else {
				return false;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}finally{
			userDAO.closeConnection();
		}
	}
}
