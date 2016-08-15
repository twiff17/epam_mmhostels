package by.epam.hostelbeta.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SessionRequestContent {
	private HashMap<String, Object> requestAttributes;
	private HashMap<String, String[]> requestParameters;
	private HashMap<String, Object> sessionAttributes;
	
	public SessionRequestContent(HttpServletRequest request){
		for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
	        requestParameters.put(entry.getKey(), entry.getValue());         
	    }
	}
	
	public void extractValues(HttpServletRequest request){
		
	}
}
