package by.epam.hostelbeta.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
