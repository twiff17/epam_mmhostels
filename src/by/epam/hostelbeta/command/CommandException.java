package by.epam.hostelbeta.command;

/**
 * The Class CommandException.
 */
@SuppressWarnings("serial")
public class CommandException extends Exception {
	
	/**
	 * Instantiates a new command exception.
	 */
	public CommandException() {
		super();
	}

	/**
	 * Instantiates a new command exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new command exception.
	 *
	 * @param message the message
	 */
	public CommandException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new command exception.
	 *
	 * @param cause the cause
	 */
	public CommandException(Throwable cause) {
		super(cause);
	}
}
