package by.epam.hostelbeta.mail;

public class MailMessageTemplate {
	public static final String acceptMessageBooking = new String(
			"Здравствуйте, <login>. Ваш заказ на брониование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Ждем вас <inDate> в 12-00. С уважением, администрация сервиса MMHostels! Приятного отдыха.");
	public static final String acceptMessageFullpayment = new String(
			"Здравствуйте, <login>. Ваш заказ на полную оплату номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Оплатите, пожалуйста, сумму <price> BYN на счет 0000-0000-0000-0000.  Ждем вас <inDate> в 12-00 с чеком об оплате. С уважением, администрация сервиса MMHostels! Приятного отдыха.");
	public static final String rejectOrderMessage = new String(
			"Здравствуйте, <login>. Ваш заказ номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> отклонен. Приносим свои извинения. С уважением, администрация сервиса MMHostels!");
	public static final String bookRoomMessage = new String(
			"Здравствуйте, <login>. Вы сделали заказ на бронирование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate>. В ближайшие часы ваш заказ будет рассмотрен и на этот email будет выслано письмо с инструкцией. С уважением, администрация сервиса MMHostels!");
	public static final String hostelDeletedFullpayment = new String(
			"Здавствуйте, <login>. Хостел \"<hostel>\" по неизвестным причинам был удален из нашей системы. Ваш заказ на полную оплату номера в данном хостеле на даты <inDate> - <outDate> аннулируется. Если вы уже оплатили заказ, мы вернем вам деньги! Свяжитесь с нашим администратором по почте #####, либо по телефону 000-00-00. Извините за предоставленные неудобства. С уважением, администрация сервиса MMHostels!");
	public static final String hostelDeletedBookingOrInQueue = new String(
			"Здавствуйте, <login>. Хостел \"<hostel>\" по неизвестным причинам был удален из нашей системы. Ваш заказ на бронирование номера в данном хостеле на даты <inDate> - <outDate> аннулируется. Извините за предоставленные неудобства. С уважением, администрация сервиса MMHostels!");
	public static String roomDeletedBookingOrInQueue = new String(
			"Здавствуйте, <login>. Номер в хостеле \"<hostel>\", который вы забронировали, по неизвестным причинам был удален из нашей системы. Ваш заказ на бронирование данного номера на даты <inDate> - <outDate> аннулируется. Извините за предоставленные неудобства. С уважением, администрация сервиса MMHostels!");
	public static String roomDeletedFullpayment = new String(
			"Здавствуйте, <login>. Номер в хостеле \"<hostel>\", который вы забронировали, по неизвестным причинам был удален из нашей системы. Ваш заказ на полную оплату данного номера на даты <inDate> - <outDate> аннулируется. Если вы уже оплатили заказ, мы вернем вам деньги! Свяжитесь с нашим администратором по почте #####, либо по телефону 000-00-00. Извините за предоставленные неудобства. С уважением, администрация сервиса MMHostels!");
}
