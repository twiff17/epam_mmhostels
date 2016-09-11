package by.epam.hostelbeta.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.hostelbeta.command.impl.common.EmptyCommand;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Command objects.
 */
public class CommandFactory {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

	/** The instance. */
	private static CommandFactory instance;

	/**
	 * Instantiates a new command factory.
	 */
	private CommandFactory() {
	}

	/**
	 * Gets the single instance of CommandFactory.
	 *
	 * @return single instance of CommandFactory
	 */
	public static CommandFactory getInstance() {
		if (instance == null) {
			instance = new CommandFactory();
		}
		return instance;
	}

	/**
	 * Gets the command.
	 *
	 * @param command the command
	 * @return the command
	 * @throws CommandException the command exception
	 */
	public ICommand getCommand(String command) throws CommandException {
		ICommand current = new EmptyCommand();
		if (command == null) {
			return current;
		}
		try {
			CommandEnum commandType = CommandEnum.valueOf(command.toUpperCase());
			current = commandType.getCurrentCommand();
		} catch (IllegalArgumentException e) {
			LOGGER.error("Unknown command " + command.toUpperCase(), e);
			throw new CommandException("Unknown command " + command.toUpperCase(), e);
		}
		return current;
	}
}
