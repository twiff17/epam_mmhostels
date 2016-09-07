package by.epam.hostelbeta.mail;

public class MailMessageTemplate {
	public static final String acceptMessageBooking = new String(
			"������������, <login>. ��� ����� �� ����������� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ���� ��� <inDate> � 12-00. � ���������, ������������� ������� MMHostels! ��������� ������.");
	public static final String acceptMessageFullpayment = new String(
			"������������, <login>. ��� ����� �� ������ ������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ��������, ����������, ����� <price> BYN �� ���� 0000-0000-0000-0000.  ���� ��� <inDate> � 12-00 � ����� �� ������. � ���������, ������������� ������� MMHostels! ��������� ������.");
	public static final String rejectOrderMessage = new String(
			"������������, <login>. ��� ����� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ��������. �������� ���� ���������. � ���������, ������������� ������� MMHostels!");
	public static final String bookRoomMessage = new String(
			"������������, <login>. �� ������� ����� �� ������������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate>. � ��������� ���� ��� ����� ����� ���������� � �� ���� email ����� ������� ������ � �����������. � ���������, ������������� ������� MMHostels!");
	public static final String hostelDeletedFullpayment = new String(
			"�����������, <login>. ������ \"<hostel>\" �� ����������� �������� ��� ������ �� ����� �������. ��� ����� �� ������ ������ ������ � ������ ������� �� ���� <inDate> - <outDate> ������������. ���� �� ��� �������� �����, �� ������ ��� ������! ��������� � ����� ��������������� �� ����� #####, ���� �� �������� 000-00-00. �������� �� ��������������� ����������. � ���������, ������������� ������� MMHostels!");
	public static final String hostelDeletedBookingOrInQueue = new String(
			"�����������, <login>. ������ \"<hostel>\" �� ����������� �������� ��� ������ �� ����� �������. ��� ����� �� ������������ ������ � ������ ������� �� ���� <inDate> - <outDate> ������������. �������� �� ��������������� ����������. � ���������, ������������� ������� MMHostels!");
	public static String roomDeletedBookingOrInQueue = new String(
			"�����������, <login>. ����� � ������� \"<hostel>\", ������� �� �������������, �� ����������� �������� ��� ������ �� ����� �������. ��� ����� �� ������������ ������� ������ �� ���� <inDate> - <outDate> ������������. �������� �� ��������������� ����������. � ���������, ������������� ������� MMHostels!");
	public static String roomDeletedFullpayment = new String(
			"�����������, <login>. ����� � ������� \"<hostel>\", ������� �� �������������, �� ����������� �������� ��� ������ �� ����� �������. ��� ����� �� ������ ������ ������� ������ �� ���� <inDate> - <outDate> ������������. ���� �� ��� �������� �����, �� ������ ��� ������! ��������� � ����� ��������������� �� ����� #####, ���� �� �������� 000-00-00. �������� �� ��������������� ����������. � ���������, ������������� ������� MMHostels!");
}
