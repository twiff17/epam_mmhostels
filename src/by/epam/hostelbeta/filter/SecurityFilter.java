package by.epam.hostelbeta.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.CommandEnum;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

@WebFilter(urlPatterns = { "/AjaxController", "/Controller" })
public class SecurityFilter implements Filter {
	private ArrayList<String> adminCommands;

	private static final String NO_ACCESS_PATH = "path.page.noaccess";
	private static final String ROLE_ADMIN = "admin";

	public SecurityFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String command = httpRequest.getParameter(Parameters.COMMAND);
		String role = (String) httpRequest.getSession().getAttribute(Parameters.ROLE);
		if (!ROLE_ADMIN.equals(role) && adminCommands.contains(command.toUpperCase())) {
			httpRequest.getRequestDispatcher(ConfigurationManager.getProperty(NO_ACCESS_PATH)).forward(httpRequest,
					httpResponse);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
		adminCommands = new ArrayList<String>();
		adminCommands.add(CommandEnum.ACCEPT_ORDER.toString());
		adminCommands.add(CommandEnum.ADD_DISCOUNT.toString());
		adminCommands.add(CommandEnum.ADD_HOSTEL.toString());
		adminCommands.add(CommandEnum.ADD_ROOM.toString());
		adminCommands.add(CommandEnum.BAN_USER.toString());
		adminCommands.add(CommandEnum.DELETE_HOSTEL.toString());
		adminCommands.add(CommandEnum.DELETE_ROOM.toString());
		adminCommands.add(CommandEnum.EDIT_HOSTEL.toString());
		adminCommands.add(CommandEnum.EDIT_ROOM.toString());
		adminCommands.add(CommandEnum.GET_HOSTEL_ADD.toString());
		adminCommands.add(CommandEnum.GET_HOSTEL_EDIT.toString());
		adminCommands.add(CommandEnum.GET_HOSTELS_ADMIN.toString());
		adminCommands.add(CommandEnum.GET_ORDERS.toString());
		adminCommands.add(CommandEnum.GET_ROOM_ADD.toString());
		adminCommands.add(CommandEnum.GET_ROOM_EDIT.toString());
		adminCommands.add(CommandEnum.GET_ROOMS_ADMIN.toString());
		adminCommands.add(CommandEnum.GET_USERS.toString());
		adminCommands.add(CommandEnum.REJECT_ORDER.toString());
		adminCommands.add(CommandEnum.UNBAN_USER.toString());
	}

}
