package by.epam.hostelbeta.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
	private long orderId;
	private long hostelId;
	private long roomId;
	private long userId;
	private LocalDateTime orderTime;
	private String status;
	private LocalDate inDate;
	private LocalDate outDate;
	private int booking;
	private double price;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getHostelId() {
		return hostelId;
	}
	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public int getBooking() {
		return booking;
	}
	public void setBooking(int booking) {
		this.booking = booking;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
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
