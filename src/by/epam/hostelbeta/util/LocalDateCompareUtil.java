package by.epam.hostelbeta.util;

import java.time.LocalDate;

/**
 * The Class LocalDateCompareUtil. Class for comparing date with current date
 */
public class LocalDateCompareUtil {

	/**
	 * Checks if a given date is before or equals a current date.
	 *
	 * @param date
	 *            the date
	 * @return true, if a given date is before or equals a current date.
	 */
	public static boolean isBeforeOrEqualsCurrentDate(LocalDate date) {
		return LocalDate.now().isAfter(date) || LocalDate.now().isEqual(date);
	}
}
