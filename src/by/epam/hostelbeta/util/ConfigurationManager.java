package by.epam.hostelbeta.util;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class ConfigurationManager. Manager for getting jsp paths
 */
public class ConfigurationManager {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(ConfigurationManager.class);

	/** The resource bundle. */
	private static ResourceBundle resourceBundle;

	/**
	 * Instantiates a new configuration manager.
	 */
	private ConfigurationManager() {

	}

	/**
	 * Gets the path to the jsp by key.
	 *
	 * @param key
	 *            the key
	 * @return the path
	 */
	public static String getProperty(String key) {
		if (resourceBundle == null) {
			try {
				resourceBundle = PropertyResourceBundle.getBundle(Parameters.CONFIG);
			} catch (MissingResourceException e) {
				LOGGER.fatal("Missing config properties!", e);
				throw new RuntimeException(e);
			}
		}
		return resourceBundle.getString(key);
	}
}
