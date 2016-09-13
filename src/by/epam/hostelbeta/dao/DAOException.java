package by.epam.hostelbeta.dao;

/**
 * The Class DAOException.
 */
@SuppressWarnings("serial")
public class DAOException extends Exception {
	
	/**
	 * Instantiates a new DAO exception.
	 */
	public DAOException() {
		super();
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param cause the cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}
}
