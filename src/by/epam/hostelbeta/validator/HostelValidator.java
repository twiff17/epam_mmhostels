package by.epam.hostelbeta.validator;

import by.epam.hostelbeta.domain.entity.Hostel;

/**
 * The Class HostelValidator. Validates object Hostel
 */
public class HostelValidator {

	/** The Constant NAME_REGEXP. */
	private static final String NAME_REGEXP = "[Р-п]{1}[Р-пр-џ \\-,]+";

	/** The Constant PHONE_REGEXP. */
	private static final String PHONE_REGEXP = "^\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";

	/** The Constant CITY_REGEXP. */
	private static final String CITY_REGEXP = "[Р-п]{1}[Р-пр-џ ]+";

	/** The Constant ADDRESS_REGEXP. */
	private static final String ADDRESS_REGEXP = ".{5,70}";

	/** The Constant PNG_FORMAT. */
	private static final String PNG_FORMAT = ".png";

	/** The Constant JPG_FORMAT. */
	private static final String JPG_FORMAT = ".jpg";

	/** The Constant GIF_FORMAT. */
	private static final String GIF_FORMAT = ".gif";

	/**
	 * Validates the hostel for adding to database
	 *
	 * @param hostel
	 *            the hostel
	 * @return true, if successful
	 */
	public static boolean addingValidate(Hostel hostel) {
		if (hostel.getAddress() == null || hostel.getCity() == null || hostel.getCountry() == null
				|| hostel.getCurrency() == null || hostel.getDescription() == null || hostel.getName() == null
				|| hostel.getPhone() == null || hostel.getStandartPrice() <= 0 || hostel.getName().length() > 30
				|| hostel.getCity().length() > 30 || hostel.getDescription().length() > 300
				|| hostel.getImageName() == null) {
			return false;
		}

		String format = hostel.getImageName().substring(hostel.getImageName().lastIndexOf("."));
		if (!PNG_FORMAT.equals(format) && !JPG_FORMAT.equals(format) && !GIF_FORMAT.equals(format)) {
			return false;
		}

		if (hostel.getName().matches(NAME_REGEXP) && hostel.getPhone().matches(PHONE_REGEXP)
				&& hostel.getCity().matches(CITY_REGEXP) && hostel.getAddress().matches(ADDRESS_REGEXP)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Validates the hostel for editing
	 *
	 * @param hostel
	 *            the hostel
	 * @return true, if successful
	 */
	public static boolean editingValidate(Hostel hostel) {
		if (hostel.getAddress() == null || hostel.getCity() == null || hostel.getCountry() == null
				|| hostel.getCurrency() == null || hostel.getDescription() == null || hostel.getName() == null
				|| hostel.getPhone() == null || hostel.getStandartPrice() <= 0 || hostel.getName().length() > 30
				|| hostel.getCity().length() > 30 || hostel.getDescription().length() > 300) {
			return false;
		}

		if (hostel.getName().matches(NAME_REGEXP) && hostel.getPhone().matches(PHONE_REGEXP)
				&& hostel.getCity().matches(CITY_REGEXP) && hostel.getAddress().matches(ADDRESS_REGEXP)) {
			return true;
		} else {
			return false;
		}
	}
}
