package by.epam.hostelbeta.validator;

import by.epam.hostelbeta.domain.entity.Room;

/**
 * The Class RoomValidator. Validates Room object
 */
public class RoomValidator {

	/**
	 * Validates room.
	 *
	 * @param room
	 *            the room
	 * @return true, if successful
	 */
	public static boolean validate(Room room) {
		if (room.getBedsNumber() <= 0 || room.getHostelId() <= 0 || room.getRoomId() <= 0 || room.getRoomType() <= 0) {
			return false;
		}
		return true;
	}
}
