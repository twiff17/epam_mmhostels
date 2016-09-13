package by.epam.hostelbeta.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Class Order. POJO for order
 */
public class Order {
	
	/** The order id. */
	private long orderId;
	
	/** The hostel id. */
	private long hostelId;
	
	/** The room id. */
	private long roomId;
	
	/** The user id. */
	private long userId;
	
	/** The order time. */
	private LocalDateTime orderTime;
	
	/** The status. */
	private String status;
	
	/** The in date. */
	private LocalDate inDate;
	
	/** The out date. */
	private LocalDate outDate;
	
	/** The booking. */
	private int booking;
	
	/** The price. */
	private double price;

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
	 * Gets the room id.
	 *
	 * @return the room id
	 */
	public long getRoomId() {
		return roomId;
	}

	/**
	 * Sets the room id.
	 *
	 * @param roomId the new room id
	 */
	public void setRoomId(long roomId) {
		this.roomId = roomId;
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
	 * Gets the booking.
	 *
	 * @return the booking
	 */
	public int getBooking() {
		return booking;
	}

	/**
	 * Sets the booking.
	 *
	 * @param booking the new booking
	 */
	public void setBooking(int booking) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + booking;
		result = prime * result + (int) (hostelId ^ (hostelId >>> 32));
		result = prime * result + ((inDate == null) ? 0 : inDate.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((outDate == null) ? 0 : outDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (roomId ^ (roomId >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		Order other = (Order) obj;
		if (booking != other.booking) {
			return false;
		}
		if (hostelId != other.hostelId) {
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
