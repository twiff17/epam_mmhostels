package by.epam.hostelbeta.util;

import java.util.Locale;
import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Enum LocaleManager.
 */
public enum LocaleManager {
	
	/** The default. */
	DEFAULT(Locale.getDefault()), 
 /** The en us. */
 EN_US(new Locale("en", "US"));
	
	/** The resource bundle. */
	private ResourceBundle resourceBundle;
	
	/** The resource name. */
	private final String RESOURCE_NAME = "pagecontent";

	/**
	 * Instantiates a new locale manager.
	 *
	 * @param locale the locale
	 */
	private LocaleManager(Locale locale) {
		resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
	}

	/**
	 * Gets the resource bundle.
	 *
	 * @return the resource bundle
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
