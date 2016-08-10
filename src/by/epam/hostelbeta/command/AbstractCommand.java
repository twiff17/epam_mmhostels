package by.epam.hostelbeta.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import by.epam.hostelbeta.service.PageService;
import by.epam.hostelbeta.service.ServiceException;

public abstract class AbstractCommand implements ICommand {
	protected void fillRequest(HttpServletRequest request, String page) throws ServiceException{
		HashMap<String, Object> attributes = PageService.getPageAttrubutes(page, request.getParameterMap());
		if(attributes != null){
			for (Map.Entry<String, Object> entry : attributes.entrySet()) {
		        request.setAttribute(entry.getKey(), entry.getValue());          
		    }
		}
	}
}
