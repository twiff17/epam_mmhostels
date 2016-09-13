package by.epam.hostelbeta.validator;

import java.time.LocalDate;

/**
 * The Class OrderValidator. Validates object Order
 */
public class OrderValidator {

	/**
	 * Validates in date and out date
	 *
	 * @param inDate
	 *            the in date
	 * @param outDate
	 *            the out date
	 * @return 0 if OK, 1 if out date isn't after in date, 2 if in date isn't
	 *         after current date
	 */
	public static int dateValidate(LocalDate inDate, LocalDate outDate) {
		if (!outDate.isAfter(inDate)) {
			return 1;
		}
		if (!inDate.isAfter(LocalDate.now())) {
			return 2;
		}
		return 0;
	}
}
