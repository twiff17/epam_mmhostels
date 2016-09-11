package by.epam.hostelbeta.util;

import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Class LocalDateCompareUtil.
 */
public class LocalDateCompareUtil {
	
	/**
	 * Checks if is after current date.
	 *
	 * @param date the date
	 * @return true, if is after current date
	 */
	public static boolean isAfterCurrentDate(LocalDate date) {
		return LocalDate.now().isAfter(date) || LocalDate.now().isEqual(date);
	}
}
