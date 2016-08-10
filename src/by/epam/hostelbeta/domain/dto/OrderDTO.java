package by.epam.hostelbeta.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDTO {
	private long userId;
	private String fullname;
	private String passport;
	private String email;
	private String phone;
	private String hostelName;
	private String country;
	private String city;
	private int roomId;
	private String roomType;
	private String status;
	private boolean booking;
	private double price;
	private double bynPrice;
	private String currency;
	private LocalDate inDate;
	private LocalDate outDate;
	private LocalDateTime orderTime;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public double getBynPrice() {
		return bynPrice;
	}
	public void setBynPrice(double bynPrice) {
		this.bynPrice = bynPrice;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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
		long temp;
		temp = Double.doubleToLongBits(bynPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((hostelName == null) ? 0 : hostelName.hashCode());
		result = prime * result + ((inDate == null) ? 0 : inDate.hashCode());
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((outDate == null) ? 0 : outDate.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + roomId;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		if (Double.doubleToLongBits(bynPrice) != Double.doubleToLongBits(other.bynPrice)) {
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
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (fullname == null) {
			if (other.fullname != null) {
				return false;
			}
		} else if (!fullname.equals(other.fullname)) {
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
		if (passport == null) {
			if (other.passport != null) {
				return false;
			}
		} else if (!passport.equals(other.passport)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
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
		return true;
	}
}
