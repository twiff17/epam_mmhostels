package by.epam.hostelbeta.domain.dto;

/**
 * The Class RoomDTO. For Room's view
 */
public class RoomDTO {

	/** The room id. */
	private int roomId;

	/** The hostel id. */
	private long hostelId;

	/** The hostel name. */
	private String hostelName;

	/** The room type. */
	private String roomType;

	/** The price. */
	private double price;

	/** The beds number. */
	private int bedsNumber;

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
	 * @param roomId
	 *            the new room id
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
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
	 * @param hostelId
	 *            the new hostel id
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
	 * @param hostelName
	 *            the new hostel name
	 */
	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
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
	 * @param roomType
	 *            the new room type
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
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
	 * @param price
	 *            the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the beds number.
	 *
	 * @return the beds number
	 */
	public int getBedsNumber() {
		return bedsNumber;
	}

	/**
	 * Sets the beds number.
	 *
	 * @param bedsNumber
	 *            the new beds number
	 */
	public void setBedsNumber(int bedsNumber) {
		this.bedsNumber = bedsNumber;
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
		result = prime * result + bedsNumber;
		result = prime * result + (int) (hostelId ^ (hostelId >>> 32));
		result = prime * result + ((hostelName == null) ? 0 : hostelName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + roomId;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
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
		RoomDTO other = (RoomDTO) obj;
		if (bedsNumber != other.bedsNumber) {
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
