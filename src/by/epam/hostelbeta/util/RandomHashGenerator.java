package by.epam.hostelbeta.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * The Class RandomHashGenerator. Class for generating random hash
 */
public class RandomHashGenerator {

	/** The random. */
	private static SecureRandom random = new SecureRandom();

	/**
	 * Creates a new random hash.
	 *
	 * @return a new random hash
	 */
	public static String nextHash() {
		return new BigInteger(130, random).toString(32);
	}
}
