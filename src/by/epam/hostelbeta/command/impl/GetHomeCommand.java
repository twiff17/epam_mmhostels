package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.util.RequestUtil;

public class GetHomeCommand extends AbstractCommand{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		try {
			return RequestUtil.createHomePage(request);
		} catch (DAOException e) {
			throw new CommandException(e);
		}
	}
}
