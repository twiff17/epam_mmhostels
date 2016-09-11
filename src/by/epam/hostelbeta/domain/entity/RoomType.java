package by.epam.hostelbeta.domain.entity;

// TODO: Auto-generated Javadoc
/**
 * The Class RoomType.
 */
public class RoomType {
	
	/** The room type id. */
	private int roomTypeId;
	
	/** The name. */
	private String name;
	
	/** The coeff. */
	private double coeff;

	/**
	 * Gets the room type id.
	 *
	 * @return the room type id
	 */
	public int getRoomTypeId() {
		return roomTypeId;
	}

	/**
	 * Sets the room type id.
	 *
	 * @param roomTypeId the new room type id
	 */
	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the coeff.
	 *
	 * @return the coeff
	 */
	public double getCoeff() {
		return coeff;
	}

	/**
	 * Sets the coeff.
	 *
	 * @param coeff the new coeff
	 */
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(coeff);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + roomTypeId;
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
		RoomType other = (RoomType) obj;
		if (Double.doubleToLongBits(coeff) != Double.doubleToLongBits(other.coeff)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (roomTypeId != other.roomTypeId) {
			return false;
		}
		return true;
	}
}
