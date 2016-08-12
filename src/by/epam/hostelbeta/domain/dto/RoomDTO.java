package by.epam.hostelbeta.domain.dto;

public class RoomDTO {
	private int roomId;
	private long hostelId;
	private String roomType;
	private double price;
	private int bedsNumber;
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public long getHostelId() {
		return hostelId;
	}
	public void setHostelId(long hostelId) {
		this.hostelId = hostelId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBedsNumber() {
		return bedsNumber;
	}
	public void setBedsNumber(int bedsNumber) {
		this.bedsNumber = bedsNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bedsNumber;
		result = prime * result + (int) (hostelId ^ (hostelId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + roomId;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
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
		RoomDTO other = (RoomDTO) obj;
		if (bedsNumber != other.bedsNumber) {
			return false;
		}
		if (hostelId != other.hostelId) {
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
		return true;
	}
}
