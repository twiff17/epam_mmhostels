package by.epam.hostelbeta.service;

import org.apache.commons.codec.digest.DigestUtils;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.UserDAO;
import by.epam.hostelbeta.entity.User;

public class LoginService {
	public static boolean checkLogin(String enterLogin, String enterPassword) throws ServiceException{
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
		return user != null;
	}
}
