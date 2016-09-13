package by.epam.hostelbeta.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class DoubleRoundUtil. Class for rounding double values
 */
public class DoubleRoundUtil {

	/**
	 * Round.
	 *
	 * @param value
	 *            the value
	 * @param places
	 *            scale of the BigDecimal value to be returned.
	 * @return a double whose scale is the specified value
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
