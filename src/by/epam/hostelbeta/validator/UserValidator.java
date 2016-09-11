package by.epam.hostelbeta.validator;

import by.epam.hostelbeta.domain.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserValidator.
 */
public class UserValidator {
	
	/** The Constant LOGIN_PASSWORD_REGEXP. */
	private static final String LOGIN_PASSWORD_REGEXP = "[A-Za-z0-9]{5,25}";
	
	/** The Constant FULLNAME_REGEXP. */
	private static final String FULLNAME_REGEXP = "[Р-пр-џ ]+|[a-zA-z ]+";
	
	/** The Constant PASSPORT_REGEXP. */
	private static final String PASSPORT_REGEXP = "[A-Z]{2}[0-9]{7}";
	
	/** The Constant EMAIL_REGEXP. */
	private static final String EMAIL_REGEXP = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
	
	/** The Constant PHONE_REGEXP. */
	private static final String PHONE_REGEXP = "^\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";

	/**
	 * Validate.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public static boolean validate(User user) {
		if (user.getEmail() == null || user.getFullname() == null || user.getLogin() == null
				|| user.getPassport() == null || user.getPassword() == null || user.getPhone() == null
				|| user.getFullname().length() > 30) {
			return false;
		}
		if (user.getLogin().matches(LOGIN_PASSWORD_REGEXP) && user.getPassword().matches(LOGIN_PASSWORD_REGEXP)
				&& user.getEmail().matches(EMAIL_REGEXP) && user.getFullname().matches(FULLNAME_REGEXP)
				&& user.getPassport().matches(PASSPORT_REGEXP) && user.getPhone().matches(PHONE_REGEXP)) {
			return true;
		} else {
			return false;
		}
	}
}
