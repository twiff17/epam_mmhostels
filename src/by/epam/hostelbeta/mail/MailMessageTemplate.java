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
			"Здравствуйте, <login>. Ваш заказ на брониование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Ждем вас <inDate> в 12-00. С уважением, администрация сервиса MMHostels! Приятного отдыха.");

	/**
	 * The Constant acceptMessageFullpayment. Template for the message about
	 * acceptance of the fullpayment order
	 */
	public static final String acceptMessageFullpayment = new String(
			"Здравствуйте, <login>. Ваш заказ на полную оплату номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> принят. Оплатите, пожалуйста, сумму <price> BYN на счет 0000-0000-0000-0000.  Ждем вас <inDate> в 12-00 с чеком об оплате. С уважением, администрация сервиса MMHostels! Приятного отдыха.");

	/**
	 * The Constant rejectBookingOrderMessage. Template for the message about
	 * rejection of the booking order
	 */
	public static final String rejectBookingOrderMessage = new String(
			"Здравствуйте, <login>. Ваш заказ номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> отклонен, т.к. <cause>. Приносим свои извинения. С уважением, администрация сервиса MMHostels!");

	/**
	 * The Constant bookRoomMessage. Template for the message about booking room
	 */
	public static final String bookRoomMessage = new String(
			"Здравствуйте, <login>. Вы сделали заказ на бронирование номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate>. В ближайшие часы ваш заказ будет рассмотрен и на этот email будет выслано письмо с инструкцией. С уважением, администрация сервиса MMHostels!");

	/**
	 * The Constant rejectFullpaymentOrderMessage. Template for the message
	 * about rejection of the fullpayment order
	 */
	public static final String rejectFullpaymentOrderMessage = new String(
			"Здравствуйте, <login>. Ваш заказ номера в хостеле \"<hostel>\" на даты: <inDate> - <outDate> отклонен, т.к. <cause>. Если вы уже оплатили заказ, мы вернем вам деньги! Свяжитесь с нашим администратором по почте #####, либо по телефону 000-00-00. Приносим свои извинения. С уважением, администрация сервиса MMHostels!");
}
