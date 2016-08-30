package by.epam.hostelbeta.mail;

public class MailMessageTemplate {
	public static String acceptMessageBooking = new String(
			"������������, <login>. ��� ����� �� ����������� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ���� ��� <inDate> � 12-00. � ���������, ������������� ������� MMHostels! ��������� ������.");
	public static String acceptMessageFullpayment = new String(
			"������������, <login>. ��� ����� �� ������ ������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ������. ��������, ����������, ����� <price> BYN �� ���� 0000-0000-0000-0000.  ���� ��� <inDate> � 12-00 � ����� �� ������. � ���������, ������������� ������� MMHostels! ��������� ������.");
	public static String rejectOrderMessage = new String(
			"������������, <login>. ��� ����� ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate> ��������. �������� ���� ���������. � ���������, ������������� ������� MMHostels!");
	public static String bookRoomMessage = new String(
			"������������, <login>. �� ������� ����� �� ������������ ������ � ������� \"<hostel>\" �� ����: <inDate> - <outDate>. � ��������� ���� ��� ����� ����� ���������� � �� ���� email ����� ������� ������ � �����������. � ���������, ������������� ������� MMHostels!");
}
