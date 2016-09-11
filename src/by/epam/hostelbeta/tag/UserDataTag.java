package by.epam.hostelbeta.tag;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.epam.hostelbeta.domain.entity.User;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDataTag.
 */
@SuppressWarnings("serial")
public class UserDataTag extends TagSupport {
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		HttpSession session = pageContext.getSession();
		User user = (User) session.getAttribute(Parameters.SESSION_USER);
		LocaleManager localeManager = (LocaleManager) session.getAttribute(Parameters.LOCALE_MANAGER);
		try {
			pageContext.getOut()
					.write(localeManager.getResourceBundle().getString(Parameters.USER_DATA_LOGIN) + user.getLogin()
							+ "<br>" + localeManager.getResourceBundle().getString(Parameters.USER_DATA_ROLE)
							+ user.getRole() + "<br>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}
