package by.epam.hostelbeta.command.impl.hostel;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

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

public class AddHostelCommand extends AbstractCommand {
	private static final String HOSTEL_ADD_PATH = "path.page.hostel-add";
	private static final String HOSTEL_PATH = "path.page.hostel";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		LocaleManager locManager = (LocaleManager) request.getSession().getAttribute(Parameters.LOCALE_MANAGER);
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

			if (HostelValidator.addValidate(hostel)) {
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
						locManager.getResourceBundle().getString(Parameters.INVALID_DATA));
				page = ConfigurationManager.getProperty(HOSTEL_ADD_PATH);
			}
		} catch (FileUploadException | ServiceException e) {
			throw new CommandException(e);
		}
		return page;
	}

	private void processUploadedFile(FileItem item, HttpServletRequest request, Hostel hostel) throws CommandException {
		File uploadedFile = null;
		String path = request.getServletContext().getRealPath("/images/hostels");
		hostel.setImageName(item.getName());
		String format = item.getName().substring(item.getName().lastIndexOf("."));
		if (format.equals(".png") || format.equals(".jpg") || format.equals(".gif")) {
			uploadedFile = new File(path + "/" + item.getName());
			try {
				uploadedFile.createNewFile();
				item.write(uploadedFile);
			} catch (Exception e) {
				throw new CommandException(e);
			}
		}
	}

	private void processFormField(FileItem item, Hostel hostel) throws CommandException {
		if (item.isFormField()) {
			try {
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
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
				default:
					break;
				}
			} catch (UnsupportedEncodingException | NumberFormatException e) {
				throw new CommandException(e);
			}
		}
	}
}
