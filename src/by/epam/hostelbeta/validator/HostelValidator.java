package by.epam.hostelbeta.validator;

import by.epam.hostelbeta.domain.entity.Hostel;

public class HostelValidator {
	private static final String NAME_REGEXP = "[Р-п]{1}[Р-пр-џ ]+";
	private static final String PHONE_REGEXP = "^\\(\\d{3}\\)\\d{3}-\\d{2}-\\d{2}$";
	private static final String CITY_REGEXP = "[Р-п]{1}[Р-пр-џ ]+";
	private static final String ADDRESS_REGEXP = ".{5,70}";

	public static boolean addValidate(Hostel hostel) {
		if (hostel.getAddress() == null || hostel.getCity() == null || hostel.getCountry() == null
				|| hostel.getCurrency() == null || hostel.getDescription() == null || hostel.getName() == null
				|| hostel.getPhone() == null || hostel.getStandartPrice() <= 0 || hostel.getName().length() > 30
				|| hostel.getCity().length() > 30 || hostel.getDescription().length() > 300
				|| hostel.getImageName() == null) {
			return false;
		}

		String format = hostel.getImageName().substring(hostel.getImageName().lastIndexOf("."));
		if (!format.equals(".png") && !format.equals(".jpg") && !format.equals(".gif")) {
			return false;
		}

		if (hostel.getName().matches(NAME_REGEXP) && hostel.getPhone().matches(PHONE_REGEXP)
				&& hostel.getCity().matches(CITY_REGEXP) && hostel.getAddress().matches(ADDRESS_REGEXP)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean editValidate(Hostel hostel) {
		if (hostel.getAddress() == null || hostel.getCity() == null || hostel.getCountry() == null
				|| hostel.getCurrency() == null || hostel.getDescription() == null || hostel.getName() == null
				|| hostel.getPhone() == null || hostel.getStandartPrice() <= 0 || hostel.getName().length() > 30
				|| hostel.getCity().length() > 30 || hostel.getDescription().length() > 300) {
			return false;
		}

		if (hostel.getName().matches(NAME_REGEXP) && hostel.getPhone().matches(PHONE_REGEXP)
				&& hostel.getCity().matches(CITY_REGEXP) && hostel.getAddress().matches(ADDRESS_REGEXP)) {
			return true;
		} else {
			return false;
		}
	}
}
