package by.epam.hostelbeta.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDTO {
	private long orderId;
	private long userId;
	private String userLogin;
	private String hostelName;
	private String country;
	private String city;
	private int roomId;
	private String roomType;
	private String status;
	private boolean booking;
	private double price;
	private LocalDate inDate;
	private LocalDate outDate;
	private LocalDateTime orderTime;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getHostelName() {
		return hostelName;
	}
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isBooking() {
		return booking;
	}
	public void setBooking(boolean booking) {
		this.booking = booking;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getInDate() {
		return inDate;
	}
	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}
	public LocalDate getOutDate() {
		return outDate;
	}
	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (booking ? 1231 : 1237);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		return result;
	}
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
