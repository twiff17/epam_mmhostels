package by.epam.hostelbeta.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand implements ICommand {

	@Override
	public ICommand setUp(HttpServletRequest request, HttpServletResponse response) {
		return this;
	}


}
