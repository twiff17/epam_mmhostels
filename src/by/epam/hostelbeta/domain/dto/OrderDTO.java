package by.epam.hostelbeta.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderDTO.
 */
public class OrderDTO {
	
	/** The order id. */
	private long orderId;
	
	/** The user id. */
	private long userId;
	
	/** The user login. */
	private String userLogin;
	
	/** The user email. */
	private String userEmail;
	
	/** The hostel id. */
	private long hostelId;
	
	/** The hostel name. */
	private String hostelName;
	
	/** The country. */
	private String country;
	
	/** The city. */
	private String city;
	
	/** The room id. */
	private int roomId;
	
	/** The room type. */
	private String roomType;
	
	/** The status. */
	private String status;
	
	/** The booking. */
	private boolean booking;
	
	/** The price. */
	private double price;
	
	/** The in date. */
	private LocalDate inDate;
	
	/** The out date. */
	private LocalDate outDate;
	
	/** The order time. */
	private LocalDateTime orderTime;

	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the user login.
	 *
	 * @return the user login
	 */
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * Sets the user login.
	 *
	 * @param userLogin the new user login
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * Gets the user email.
	 *
	 * @return the user email
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * Sets the user email.
	 *
	 * @param userEmail the new user email
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

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
	 * @param hostelId the new hostel id
	 */
	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}

	/**
	 * Gets the hostel name.
	 *
	 * @return the hostel name
	 */
	public String getHostelName() {
		return hostelName;
	}

	/**
	 * Sets the hostel name.
	 *
	 * @param hostelName the new hostel name
	 */
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
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
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the room id.
	 *
	 * @return the room id
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * Sets the room id.
	 *
	 * @param roomId the new room id
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * Gets the room type.
	 *
	 * @return the room type
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * Sets the room type.
	 *
	 * @param roomType the new room type
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Checks if is booking.
	 *
	 * @return true, if is booking
	 */
	public boolean isBooking() {
		return booking;
	}

	/**
	 * Sets the booking.
	 *
	 * @param booking the new booking
	 */
	public void setBooking(boolean booking) {
		this.booking = booking;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the in date.
	 *
	 * @return the in date
	 */
	public LocalDate getInDate() {
		return inDate;
	}

	/**
	 * Sets the in date.
	 *
	 * @param inDate the new in date
	 */
	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	/**
	 * Gets the out date.
	 *
	 * @return the out date
	 */
	public LocalDate getOutDate() {
		return outDate;
	}

	/**
	 * Sets the out date.
	 *
	 * @param outDate the new out date
	 */
	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}

	/**
	 * Gets the order time.
	 *
	 * @return the order time
	 */
	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	/**
	 * Sets the order time.
	 *
	 * @param orderTime the new order time
	 */
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (booking ? 1231 : 1237);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (int) (hostelId ^ (hostelId >>> 32));
		result = prime * result + ((hostelName == null) ? 0 : hostelName.hashCode());
		result = prime * result + ((inDate == null) ? 0 : inDate.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((outDate == null) ? 0 : outDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + roomId;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		OrderDTO other = (OrderDTO) obj;
		if (booking != other.booking) {
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
		if (hostelId != other.hostelId) {
			return false;
		}
		if (hostelName == null) {
			if (other.hostelName != null) {
				return false;
			}
		} else if (!hostelName.equals(other.hostelName)) {
			return false;
		}
		if (inDate == null) {
			if (other.inDate != null) {
				return false;
			}
		} else if (!inDate.equals(other.inDate)) {
			return false;
		}
		if (orderId != other.orderId) {
			return false;
		}
		if (orderTime == null) {
			if (other.orderTime != null) {
				return false;
			}
		} else if (!orderTime.equals(other.orderTime)) {
			return false;
		}
		if (outDate == null) {
			if (other.outDate != null) {
				return false;
			}
		} else if (!outDate.equals(other.outDate)) {
			return false;
		}
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price)) {
			return false;
		}
		if (roomId != other.roomId) {
			return false;
		}
		if (roomType == null) {
			if (other.roomType != null) {
				return false;
			}
		} else if (!roomType.equals(other.roomType)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (userEmail == null) {
			if (other.userEmail != null) {
				return false;
			}
		} else if (!userEmail.equals(other.userEmail)) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		if (userLogin == null) {
			if (other.userLogin != null) {
				return false;
			}
		} else if (!userLogin.equals(other.userLogin)) {
			return false;
		}
		return true;
	}
}
