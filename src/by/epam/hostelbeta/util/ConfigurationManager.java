package by.epam.hostelbeta.util;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationManager {
	static final Logger LOGGER = LogManager.getLogger(ConfigurationManager.class);

	private static ResourceBundle resourceBundle;

	private ConfigurationManager() {

	}

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
