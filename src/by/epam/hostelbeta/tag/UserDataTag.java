package by.epam.hostelbeta.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

@SuppressWarnings("serial")
public class UserDataTag extends TagSupport {
	@Override
	public int doStartTag() throws JspException {
		HttpSession session = pageContext.getSession();
		String login = (String) session.getAttribute(Parameters.LOGIN);
		String role = (String) session.getAttribute(Parameters.ROLE);
		LocaleManager localeManager = (LocaleManager) session.getAttribute(Parameters.LOCALE_MANAGER);
		try {
			pageContext.getOut().write(localeManager.getResourceBundle().getString(Parameters.USER_DATA_LOGIN) + login
					+ "<br>" + localeManager.getResourceBundle().getString(Parameters.USER_DATA_ROLE) + role + "<br>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}
