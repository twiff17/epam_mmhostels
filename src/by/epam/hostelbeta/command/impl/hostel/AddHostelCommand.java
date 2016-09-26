package by.epam.hostelbeta.command.impl.hostel;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import by.epam.hostelbeta.command.AbstractCommand;
import by.epam.hostelbeta.command.CommandException;
import by.epam.hostelbeta.domain.entity.Country;
import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.service.CountryService;
import by.epam.hostelbeta.service.CurrencyService;
import by.epam.hostelbeta.service.HostelService;
import by.epam.hostelbeta.service.ServiceException;
import by.epam.hostelbeta.util.ConfigurationManager;
import by.epam.hostelbeta.util.LocaleManager;
import by.epam.hostelbeta.util.Parameters;
import by.epam.hostelbeta.validator.HostelValidator;

/**
 * The Class AddHostelCommand. Fills the object Hostel and saves it to the
 * database
 */
public class AddHostelCommand extends AbstractCommand {

	/** The Constant HOSTEL_ADD_PATH. The path to the adding hostel page */
	private static final String HOSTEL_ADD_PATH = "path.page.hostel-add";

	/** The Constant HOSTEL_PATH. The path to the hostel management page */
	private static final String HOSTEL_PATH = "path.page.hostel";

	/** The Constant ENCODING. */
	private static final String ENCODING = "UTF-8";

	/** The Constant PNG_FORMAT. */
	private static final String PNG_FORMAT = ".png";

	/** The Constant JPG_FORMAT. */
	private static final String JPG_FORMAT = ".jpg";

	/** The Constant GIF_FORMAT. */
	private static final String GIF_FORMAT = ".gif";

	/** The Constant HOSTELS_IMAGES_PATH. The path to the images of hostels */
	private static final String HOSTELS_IMAGES_PATH = "/images/hostels";

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.epam.hostelbeta.command.ICommand#execute(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager localeManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
		String page = null;
		Hostel hostel = new Hostel();
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					processFormField(item, hostel);
				} else {
					processUploadedFile(item, request, hostel);
				}
			}
			if (request.getSession().getAttribute(Parameters.FORM_HASH) == null
					|| !request.getSession().getAttribute(Parameters.FORM_HASH).equals(hostel.getHash())) {
				if (HostelValidator.addingValidate(hostel)) {
					request.getSession().setAttribute(Parameters.FORM_HASH, hostel.getHash());
					HostelService.addHostel(hostel);

					List<Hostel> hostels = HostelService.getAllHostels();
					request.setAttribute(Parameters.HOSTEL_LIST, hostels);
					page = ConfigurationManager.getProperty(HOSTEL_PATH);

				} else {
					List<Country> countries = CountryService.getAllCountries();
					List<Currency> currencyList = CurrencyService.getAllCurrency();
					request.setAttribute(Parameters.COUNTRY_LIST, countries);
					request.setAttribute(Parameters.CURRENCY_LIST, currencyList);
					request.setAttribute(Parameters.ERROR_ADD_HOSTEL_MESSAGE,
							localeManager.getResourceBundle().getString(Parameters.INVALID_DATA));
					page = ConfigurationManager.getProperty(HOSTEL_ADD_PATH);
				}
			} else {
				List<Hostel> hostels = HostelService.getAllHostels();
				request.setAttribute(Parameters.HOSTEL_LIST, hostels);
				page = ConfigurationManager.getProperty(HOSTEL_PATH);
			}
		} catch (FileUploadException | ServiceException e) {
			throw new CommandException(e);
		} catch (MissingResourceException e) {
			throw new CommandException("Couldn't find page path in properties file", e);
		}
		return page;
	}

	/**
	 * Process uploaded the image of hostel.
	 *
	 * @param item
	 *            - uploadable file
	 * @param request
	 *            - the request
	 * @param hostel
	 *            - the hostel
	 * @throws CommandException
	 *             the command exception
	 */
	private void processUploadedFile(FileItem item, HttpServletRequest request, Hostel hostel) throws CommandException {
		File uploadedFile = null;
		String path = request.getServletContext().getRealPath(HOSTELS_IMAGES_PATH);
		hostel.setImageName(item.getName());
		String format = item.getName().substring(item.getName().lastIndexOf("."));
		if (format.equals(PNG_FORMAT) || format.equals(JPG_FORMAT) || format.equals(GIF_FORMAT)) {
			uploadedFile = new File(path + "/" + item.getName());
			try {
				uploadedFile.createNewFile();
				item.write(uploadedFile);
			} catch (Exception e) {
				throw new CommandException(e);
			}
		}
	}

	/**
	 * Process simple form field.
	 *
	 * @param item
	 *            - the field of the form
	 * @param hostel
	 *            - the hostel
	 * @throws CommandException
	 *             the command exception
	 */
	private void processFormField(FileItem item, Hostel hostel) throws CommandException {
		if (item.isFormField()) {
			try {
				String name = item.getFieldName();
				String value = item.getString(ENCODING);
				switch (name) {
				case Parameters.NAME:
					hostel.setName(value);
					break;
				case Parameters.ADDRESS:
					hostel.setAddress(value);
					break;
				case Parameters.CITY:
					hostel.setCity(value);
					break;
				case Parameters.COUNTRY:
					hostel.setCountry(value);
					break;
				case Parameters.CURRENCY:
					hostel.setCurrency(value);
					break;
				case Parameters.DESCRIPTION:
					hostel.setDescription(value);
					break;
				case Parameters.PHONE:
					hostel.setPhone(value);
					break;
				case Parameters.STANDART_PRICE:
					hostel.setStandartPrice(Integer.parseInt(value));
					break;
				case Parameters.HASH:
					hostel.setHash(value);
				default:
					break;
				}
			} catch (UnsupportedEncodingException | NumberFormatException e) {
				throw new CommandException(e);
			}
		}
	}
}
