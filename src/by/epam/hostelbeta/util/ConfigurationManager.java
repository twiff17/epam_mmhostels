package by.epam.hostelbeta.util;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(Parameters.CONFIG);
	private ConfigurationManager(){
		
	}
	public static String getProperty(String key){
		return resourceBundle.getString(key);
	}
}
