package by.epam.hostelbeta.mail;

// TODO: Auto-generated Javadoc
/**
 * The Class MailMessageTemplate.
 */
public class MailMessageTemplate {
	
	/** The Constant acceptMessageBooking. */
	public static final String acceptMessageBooking = new String(
			"Здравствуйте, <login>. Ваш заказ на брониование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Ждем вас <inDate> в 12-00. С уважением, администрация сервиса MMHostels! Приятного отдыха.");
	
	/** The Constant acceptMessageFullpayment. */
	public static final String acceptMessageFullpayment = new String(
			"Здравствуйте, <login>. Ваш заказ на полную оплату номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Оплатите, пожалуйста, сумму <price> BYN на счет 0000-0000-0000-0000.  Ждем вас <inDate> в 12-00 с чеком об оплате. С уважением, администрация сервиса MMHostels! Приятного отдыха.");
	
	/** The Constant rejectBookingOrderMessage. */
	public static final String rejectBookingOrderMessage = new String(
			"Здравствуйте, <login>. Ваш заказ номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> отклонен, т.к. <cause>. Приносим свои извинения. С уважением, администрация сервиса MMHostels!");
	
	/** The Constant bookRoomMessage. */
	public static final String bookRoomMessage = new String(
			"Здравствуйте, <login>. Вы сделали заказ на бронирование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate>. В ближайшие часы ваш заказ будет рассмотрен и на этот email будет выслано письмо с инструкцией. С уважением, администрация сервиса MMHostels!");
	
	/** The reject fullpayment order message. */
	public static String rejectFullpaymentOrderMessage = new String(
			"Здравствуйте, <login>. Ваш заказ номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> отклонен, т.к. <cause>. Если вы уже оплатили заказ, мы вернем вам деньги! Свяжитесь с нашим администратором по почте #####, либо по телефону 000-00-00. Приносим свои извинения. С уважением, администрация сервиса MMHostels!");
}
