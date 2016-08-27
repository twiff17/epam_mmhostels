package by.epam.hostelbeta.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomHashGenerator {
	private static SecureRandom random = new SecureRandom();

	public static String nextHash() {
		return new BigInteger(130, random).toString(32);
	}
}
