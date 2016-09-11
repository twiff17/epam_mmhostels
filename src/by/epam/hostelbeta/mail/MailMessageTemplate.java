package by.epam.hostelbeta.mail;

// TODO: Auto-generated Javadoc
/**
 * The Class MailMessageTemplate.
 */
public class MailMessageTemplate {
	
	/** The Constant acceptMessageBooking. */
	public static final String acceptMessageBooking = new String(
			"������������, <login>. ��� ����� �� ����������� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ���� ��� <inDate> � 12-00. � ���������, ������������� ������� MMHostels! ��������� ������.");
	
	/** The Constant acceptMessageFullpayment. */
	public static final String acceptMessageFullpayment = new String(
			"������������, <login>. ��� ����� �� ������ ������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ��������, ����������, ����� <price> BYN �� ���� 0000-0000-0000-0000.  ���� ��� <inDate> � 12-00 � ����� �� ������. � ���������, ������������� ������� MMHostels! ��������� ������.");
	
	/** The Constant rejectBookingOrderMessage. */
	public static final String rejectBookingOrderMessage = new String(
			"������������, <login>. ��� ����� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ��������, �.�. <cause>. �������� ���� ���������. � ���������, ������������� ������� MMHostels!");
	
	/** The Constant bookRoomMessage. */
	public static final String bookRoomMessage = new String(
			"������������, <login>. �� ������� ����� �� ������������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate>. � ��������� ���� ��� ����� ����� ���������� � �� ���� email ����� ������� ������ � �����������. � ���������, ������������� ������� MMHostels!");
	
	/** The reject fullpayment order message. */
	public static String rejectFullpaymentOrderMessage = new String(
			"������������, <login>. ��� ����� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ��������, �.�. <cause>. ���� �� ��� �������� �����, �� ������ ��� ������! ��������� � ����� ��������������� �� ����� #####, ���� �� �������� 000-00-00. �������� ���� ���������. � ���������, ������������� ������� MMHostels!");
}
