function click_o_price() {
	$('#o_price').addClass('active');
	$('#o_date').removeClass('active');
	$('#form_by_date').addClass('invisible');
	$('#form_by_price').removeClass('invisible');
	
}
function click_o_date() {
	$('#o_price').removeClass('active');
	$('#o_date').addClass('active');
	$('#form_by_price').addClass('invisible');
	$('#form_by_date').removeClass('invisible');
}

$(document).ready(function() {
	$("#loginInput").blur(function() {
		$.post('AjaxController', {
			command : "check_login",
			login : $(this).val()
		}, function(serverResponse) {
			$("#loginCheckResult").html(serverResponse);
		})
	});

	PopUpHide(false);
	PopUpTwoHide(false);
	
	var today = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
	var tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000 + 24 * 60 * 60 * 1000);
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();
	var tomday = tomorrow.getDate();
	var tommonth = tomorrow.getMonth() + 1;
	var tomyear = tomorrow.getFullYear();
	if(dd<10){dd='0'+dd} if(mm<10){mm='0'+mm} today = yyyy+'-'+mm+'-'+dd;
	if(tomday<10){tomday='0'+tomday} if(tommonth<10){tommonth='0'+tommonth} tomorrow = tomyear+'-'+tommonth+'-'+tomday;
	$('#inDate').attr('value', today);
	$('#outDate').attr('value', tomorrow);
	$('#inDateSearch').attr('value', today);
	$('#outDateSearch').attr('value', tomorrow);
});
function PopUpShow(mess) {
	$("#message").html(mess);
	$("#popup1").show();
}
function PopUpHide(reloadPage) {
	$("#popup1").hide();
	if (reloadPage) {
		location.reload(true);
	}
}
function PopUpTwoShow(hostelId, hostelName) {
	document.getElementById("hostelId").value = hostelId;
	document.getElementById("hostelName").value = hostelName;
	$("#popup2").show();
}
function PopUpTwoHide(reloadPage) {
	$("#popup2").hide();
	if (reloadPage) {
		location.reload(true);
	}
}
function ErrorPopUpHide(reloadPage) {
	$("#error_popup").hide();
	if (reloadPage) {
		location.reload(true);
	}
}
function acceptOrder(orderId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			orderId : orderId,
			command : "accept_order"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function rejectOrder(orderId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			orderId : orderId,
			command : "reject_order"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function cancelOrder(orderId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			orderId : orderId,
			command : "cancel_order"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function changeLocale(locale) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			locale : locale,
			command : "change_locale"
		}
	}).done(function(data) {
		location.reload();
	});
}
function deleteHostel(hostelId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			hostelId : hostelId,
			command : "delete_hostel"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function deleteRoom(hostelId, roomId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			hostelId : hostelId,
			roomId : roomId,
			command : "delete_room"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function banUser(userId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			userId : userId,
			command : "ban_user"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function unBanUser(userId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			userId : userId,
			command : "unban_user"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function addDiscountUser(userId) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			userId : userId,
			command : "add_discount"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}
function bookRoom(hostelId, roomId, inDate, outDate, price, booking) {
	$.ajax({
		url : "AjaxController",
		method : "post",
		data : {
			hostelId : hostelId,
			roomId : roomId,
			inDate : inDate,
			outDate : outDate,
			price : price,
			booking : booking,
			command : "book_room"
		}
	}).done(function(data) {
		PopUpShow(data);
	});
}