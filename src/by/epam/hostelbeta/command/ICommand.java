package by.epam.hostelbeta.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {
	public ICommand setUp(HttpServletRequest request, HttpServletResponse response);
	String execute(HttpServletRequest request, HttpServletResponse response);
}
