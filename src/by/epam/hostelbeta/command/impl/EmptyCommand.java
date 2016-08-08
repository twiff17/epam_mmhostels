package by.epam.hostelbeta.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.ICommand;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

public class EmptyCommand implements ICommand {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response){
		return	ConfigurationManager.getProperty(Parameters.INDEX_PATH);
	}
}
