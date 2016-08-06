package by.epam.hostelbeta.dao;

public class DAOException extends Exception {
	private static final long serialVersionUID = -3674246371233773772L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
}
