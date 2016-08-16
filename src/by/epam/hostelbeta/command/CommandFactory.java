package by.epam.hostelbeta.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.hostelbeta.command.impl.EmptyCommand;

public class CommandFactory {
	static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

	private static CommandFactory instance;

	private CommandFactory() {
	}

	public static CommandFactory getInstance() {
		if (instance == null) {
			instance = new CommandFactory();
		}
		return instance;
	}

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
