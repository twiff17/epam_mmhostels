package by.epam.hostelbeta.domain.entity;

public class Room {
	private long hostelId;
	private long roomId;
	private int roomType;
	private int bedsNumber;

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

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
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
		result = prime * result + (int) (roomId ^ (roomId >>> 32));
		result = prime * result + roomType;
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
