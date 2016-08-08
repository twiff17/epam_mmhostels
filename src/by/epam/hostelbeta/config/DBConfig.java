package by.epam.hostelbeta.config;

import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBConfig {
	static final Logger LOGGER = LogManager.getLogger(DBConfig.class);
	
	public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    
    private static final String DB_BOUNDLE_NAME = "jdbc";
    private static ResourceBundle bundle;
    
    private DBConfig(){
    	
    }
    
    public static String getProperty(String key) {
        if(bundle == null) {
        	try{
        		bundle = PropertyResourceBundle.getBundle(DB_BOUNDLE_NAME);
        	}catch(MissingResourceException e){
        		LOGGER.fatal("Missing jdbc properties! Could not init connection to DB", e);
        		throw new RuntimeException(e);
        	}
        }
        return bundle.getString(key);
    }
}
