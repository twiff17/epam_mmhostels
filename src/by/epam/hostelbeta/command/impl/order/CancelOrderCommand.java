package by.epam.hostelbeta.command.impl.order;

import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.service.OrderService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;

// TODO: Auto-generated Javadoc
/**
 * The Class CancelOrderCommand.
 */
public class CancelOrderCommand extends AbstractCommand {
	
	/** The Constant PROPERTY_NO_FOUND. */
	private static final String PROPERTY_NO_FOUND = "???not_found???";
	
	/* (non-Javadoc)
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String message;
		try {
			long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
			if (OrderService.cancelOrder(orderId)) {
				message = locManager.getResourceBundle().getString(Parameters.OPERATION_SUCCESS);
			}else{
				message = locManager.getResourceBundle().getString(Parameters.CANT_CANCEL_ORDER);
			}
		} catch (ServiceException | NumberFormatException e) {
			throw new CommandException(e);
		} catch(MissingResourceException e){
			message = PROPERTY_NO_FOUND;
		}
		return message;
	}
}
