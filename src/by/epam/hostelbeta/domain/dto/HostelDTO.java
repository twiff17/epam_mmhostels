package by.epam.hostelbeta.domain.dto;

/**
 * The Class HostelDTO. For Hostel's view
 */
public class HostelDTO {

	/** The hostel id. */
	private long hostelId;

	/** The name. */
	private String name;

	/** The country. */
	private String country;

	/** The currency. */
	private String currency;

	/** The city. */
	private String city;

	/** The phone. */
	private String phone;

	/** The description. */
	private String description;

	/** The address. */
	private String address;

	/** The min price. */
	private int minPrice;

	/** The max price. */
	private int maxPrice;

	/** The room types. */
	private String roomTypes;

	/** The image name. */
	private String imageName;

	/**
	 * Gets the hostel id.
	 *
	 * @return the hostel id
	 */
	public long getHostelId() {
		return hostelId;
	}

	/**
	 * Sets the hostel id.
	 *
	 * @param hostelId
	 *            the new hostel id
	 */
	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Sets the currency.
	 *
	 * @param currency
	 *            the new currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the min price.
	 *
	 * @return the min price
	 */
	public int getMinPrice() {
		return minPrice;
	}

	/**
	 * Sets the min price.
	 *
	 * @param minPrice
	 *            the new min price
	 */
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	/**
	 * Gets the max price.
	 *
	 * @return the max price
	 */
	public int getMaxPrice() {
		return maxPrice;
	}

	/**
	 * Sets the max price.
	 *
	 * @param maxPrice
	 *            the new max price
	 */
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * Gets the room types.
	 *
	 * @return the room types
	 */
	public String getRoomTypes() {
		return roomTypes;
	}

	/**
	 * Sets the room types.
	 *
	 * @param roomTypes
	 *            the new room types
	 */
	public void setRoomTypes(String roomTypes) {
		this.roomTypes = roomTypes;
	}

	/**
	 * Gets the image name.
	 *
	 * @return the image name
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Sets the image name.
	 *
	 * @param imageName
	 *            the new image name
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (hostelId ^ (hostelId >>> 32));
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + maxPrice;
		result = prime * result + minPrice;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((roomTypes == null) ? 0 : roomTypes.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HostelDTO other = (HostelDTO) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (currency == null) {
			if (other.currency != null) {
				return false;
			}
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (hostelId != other.hostelId) {
			return false;
		}
		if (imageName == null) {
			if (other.imageName != null) {
				return false;
			}
		} else if (!imageName.equals(other.imageName)) {
			return false;
		}
		if (maxPrice != other.maxPrice) {
			return false;
		}
		if (minPrice != other.minPrice) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		if (roomTypes == null) {
			if (other.roomTypes != null) {
				return false;
			}
		} else if (!roomTypes.equals(other.roomTypes)) {
			return false;
		}
		return true;
	}

}
