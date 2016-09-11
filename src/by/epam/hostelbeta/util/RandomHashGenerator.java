package by.epam.hostelbeta.util;

import java.math.BigInteger;
import java.security.SecureRandom;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomHashGenerator.
 */
public class RandomHashGenerator {
	
	/** The random. */
	private static SecureRandom random = new SecureRandom();

	/**
	 * Next hash.
	 *
	 * @return the string
	 */
	public static String nextHash() {
		return new BigInteger(130, random).toString(32);
	}
}
