package by.epam.hostelbeta.mail;

/**
 * The Class MailMessageTemplate. Class for messages template storage
 */
public class MailMessageTemplate {

	/**
	 * The Constant acceptMessageBooking. Template for the message about
	 * acceptance of the booking order
	 */
	public static final String acceptMessageBooking = new String(
			"������������, <login>. ��� ����� �� ����������� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ���� ��� <inDate> � 12-00. � ���������, ������������� ������� MMHostels! ��������� ������.");

	/**
	 * The Constant acceptMessageFullpayment. Template for the message about
	 * acceptance of the fullpayment order
	 */
	public static final String acceptMessageFullpayment = new String(
			"������������, <login>. ��� ����� �� ������ ������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ��������, ����������, ����� <price> BYN �� ���� 0000-0000-0000-0000.  ���� ��� <inDate> � 12-00 � ����� �� ������. � ���������, ������������� ������� MMHostels! ��������� ������.");

	/**
	 * The Constant rejectBookingOrderMessage. Template for the message about
	 * rejection of the booking order
	 */
	public static final String rejectBookingOrderMessage = new String(
			"������������, <login>. ��� ����� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ��������, �.�. <cause>. �������� ���� ���������. � ���������, ������������� ������� MMHostels!");

	/**
	 * The Constant bookRoomMessage. Template for the message about booking room
	 */
	public static final String bookRoomMessage = new String(
			"������������, <login>. �� ������� ����� �� ������������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate>. � ��������� ���� ��� ����� ����� ���������� � �� ���� email ����� ������� ������ � �����������. � ���������, ������������� ������� MMHostels!");

	/**
	 * The Constant rejectFullpaymentOrderMessage. Template for the message
	 * about rejection of the fullpayment order
	 */
	public static final String rejectFullpaymentOrderMessage = new String(
			"������������, <login>. ��� ����� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ��������, �.�. <cause>. ���� �� ��� �������� �����, �� ������ ��� ������! ��������� � ����� ��������������� �� ����� #####, ���� �� �������� 000-00-00. �������� ���� ���������. � ���������, ������������� ������� MMHostels!");
}
