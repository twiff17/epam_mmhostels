package by.epam.hostelbeta.mail;

public class MailMessageTemplate {
	public static String acceptMessageBooking = new String(
			"Здравствуйте, <login>. Ваш заказ на брониование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Ждем вас <inDate> в 12-00. С уважением, администрация сервиса MMHostels! Приятного отдыха.");
	public static String acceptMessageFullpayment = new String(
			"Здравствуйте, <login>. Ваш заказ на полную оплату номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Оплатите, пожалуйста, сумму <price> BYN на счет 0000-0000-0000-0000.  Ждем вас <inDate> в 12-00 с чеком об оплате. С уважением, администрация сервиса MMHostels! Приятного отдыха.");
	public static String rejectOrderMessage = new String(
			"Здравствуйте, <login>. Ваш заказ номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> отклонен. Приносим свои извинения. С уважением, администрация сервиса MMHostels!");
	public static String bookRoomMessage = new String(
			"Здравствуйте, <login>. Вы сделали заказ на бронирование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate>. В ближайшие часы ваш заказ будет рассмотрен и на этот email будет выслано письмо с инструкцией. С уважением, администрация сервиса MMHostels!");
}
