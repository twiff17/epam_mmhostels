package by.epam.hostelbeta.service;

import org.apache.commons.codec.digest.DigestUtils;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.UserDAO;
import by.epam.hostelbeta.domain.entity.User;

public class LoginService {
	public static User checkLoginPassword(String enterLogin, String enterPassword) throws ServiceException{
		UserDAO userDAO = new UserDAO();
		User user = null;
		String encryptedPassword = DigestUtils.md5Hex(enterPassword);
		try {
			user = userDAO.findByLoginAndPassword(enterLogin, encryptedPassword);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}finally{
			userDAO.closeConnection();
		}
		return user;
	}
	
	public static boolean checkLogin(String login) throws ServiceException{
		UserDAO userDAO = new UserDAO();
		boolean isLoginExists = false;
		try {
			isLoginExists = userDAO.checkLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}finally{
			userDAO.closeConnection();
		}
		return isLoginExists;
	}
}
