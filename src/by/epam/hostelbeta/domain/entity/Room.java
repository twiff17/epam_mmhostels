package by.epam.hostelbeta.domain.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Room.
 */
public class Room {
	
	/** The hostel id. */
	private long hostelId;
	
	/** The room id. */
	private long roomId;
	
	/** The room type. */
	private int roomType;
	
	/** The beds number. */
	private int bedsNumber;

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
	 * Gets the room type.
	 *
	 * @return the room type
	 */
	public int getRoomType() {
		return roomType;
	}

	/**
	 * Sets the room type.
	 *
	 * @param roomType the new room type
	 */
	public void setRoomType(int roomType) {
		this.roomType = roomType;
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
	 * @param bedsNumber the new beds number
	 */
	public void setBedsNumber(int bedsNumber) {
		this.bedsNumber = bedsNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bedsNumber;
		result = prime * result + (int) (hostelId ^ (hostelId >>> 32));
		result = prime * result + (int) (roomId ^ (roomId >>> 32));
		result = prime * result + roomType;
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
		Room other = (Room) obj;
		if (bedsNumber != other.bedsNumber) {
			return false;
		}
		if (hostelId != other.hostelId) {
			return false;
		}
		if (roomId != other.roomId) {
			return false;
		}
		if (roomType != other.roomType) {
			return false;
		}
		return true;
	}
}
