package by.epam.hostelbeta.config;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class DBConfig. Class for database configuration
 */
public class DBConfig {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(DBConfig.class);

	/** The Constant URL. */
	public static final String URL = "url";

	/** The Constant USER. */
	public static final String USER = "user";

	/** The Constant PASSWORD. */
	public static final String PASSWORD = "password";

	/**
	 * The Constant DB_BOUNDLE_NAME. The name of properties file with DB
	 * configuration
	 */
	private static final String DB_BOUNDLE_NAME = "jdbc";

	/** The bundle. */
	private static ResourceBundle bundle;

	/**
	 * Instantiates a new DB config.
	 */
	private DBConfig() {

	}

	/**
	 * Gets the property from jdbc.properties.
	 *
	 * @param key
	 *            the key of the property
	 * @return the property
	 */
	public static String getProperty(String key) {
		if (bundle == null) {
			try {
				bundle = PropertyResourceBundle.getBundle(DB_BOUNDLE_NAME);
			} catch (MissingResourceException e) {
				LOGGER.fatal("Missing jdbc properties! Could not init connection to DB", e);
				throw new RuntimeException(e);
			}
		}
		return bundle.getString(key);
	}
}
