package by.epam.hostelbeta.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

// TODO: Auto-generated Javadoc
/**
 * The Class DoubleRoundUtil.
 */
public class DoubleRoundUtil {
	
	/**
	 * Round.
	 *
	 * @param value the value
	 * @param places the places
	 * @return the double
	 */
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
