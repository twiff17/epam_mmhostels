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
import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.Parameters;

/**
 * The Class SecurityFilter. Filter for authentication user and prohibition of
 * unauthorized commands ñall
 */
@WebFilter(urlPatterns = { "/AjaxController", "/Controller" })
public class SecurityFilter implements Filter {

	/** The admin commands. */
	private ArrayList<String> adminCommands;

	/** The client commands. */
	private ArrayList<String> clientCommands;

	/** The Constant NO_ACCESS_PATH. */
	private static final String NO_ACCESS_PATH = "path.page.noaccess";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "admin";

	/** The Constant ROLE_CLIENT. */
	private static final String ROLE_CLIENT = "client";

	/**
	 * Instantiates a new security filter.
	 */
	public SecurityFilter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String command = httpRequest.getParameter(Parameters.COMMAND);
		User user = (User) httpRequest.getSession().getAttribute(Parameters.SESSION_USER);
		String role = null;
		if (user != null) {
			role = user.getRole();
		}
		if (command != null && ((role == null
				&& (adminCommands.contains(command.toUpperCase()) || clientCommands.contains(command.toUpperCase())))
				|| (role != null && ((!ROLE_ADMIN.equals(role) && adminCommands.contains(command.toUpperCase()))
						|| (!ROLE_CLIENT.equals(role) && clientCommands.contains(command.toUpperCase())))))) {

			httpRequest.getRequestDispatcher(ConfigurationManager.getProperty(NO_ACCESS_PATH)).forward(httpRequest,
					httpResponse);

		} else {
			chain.doFilter(request, response);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		adminCommands = new ArrayList<String>();
		clientCommands = new ArrayList<String>();
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

		clientCommands.add(CommandEnum.BOOK_ROOM.toString());
		clientCommands.add(CommandEnum.CANCEL_ORDER.toString());
		clientCommands.add(CommandEnum.GET_CABINET.toString());
	}
}
