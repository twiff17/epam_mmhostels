package by.epam.hostelbeta.util;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleManager {
	DEFAULT(Locale.getDefault()), EN_US(new Locale("en", "US"));
	private ResourceBundle resourceBundle;
	private final String RESOURCE_NAME = "pagecontent";

	private LocaleManager(Locale locale) {
		resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
}
