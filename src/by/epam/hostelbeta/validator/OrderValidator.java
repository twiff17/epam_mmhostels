package by.epam.hostelbeta.validator;

import java.time.LocalDate;

public class OrderValidator {
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
