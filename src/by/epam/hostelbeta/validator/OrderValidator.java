package by.epam.hostelbeta.validator;

import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderValidator.
 */
public class OrderValidator {
	
	/**
	 * Date validate.
	 *
	 * @param inDate the in date
	 * @param outDate the out date
	 * @return the int
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
