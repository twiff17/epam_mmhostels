package by.epam.hostelbeta.util;

import java.time.LocalDate;

public class LocalDateCompareUtil {
	public static boolean isAfterCurrentDate(LocalDate date) {
		return LocalDate.now().isAfter(date) || LocalDate.now().isEqual(date);
	}
}
