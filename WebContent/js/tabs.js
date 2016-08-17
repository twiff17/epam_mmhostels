function click_o_hotel() {
	$('#o_hotel').addClass('active');
	$('#o_ticket').removeClass('active');
	$('#start').addClass('invisible');
	$('#finish').addClass('invisible');
	$('#type').addClass('invisible');
	$('#hostelCity').removeClass('invisible');
	$('#roomCount').removeClass('invisible');
}
function click_o_ticket() {
	$('#o_hotel').removeClass('active');
	$('#o_ticket').addClass('active');
	$('#hostelCity').addClass('invisible');
	$('#roomCount').addClass('invisible');
	$('#start').removeClass('invisible');
	$('#finish').removeClass('invisible');
	$('#type').removeClass('invisible');
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
});
function PopUpShow(mess) {
	$("#message").html(mess);
	$("#popup1").show();
}
function PopUpHide(reloadPage) {
	$("#popup1").hide();
	if (reloadPage) {
		location.reload();
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
function addEditHostel() {
	var msg = $('#hostel-form').serialize();
	$.ajax({
		type : 'POST',
		url : 'AjaxController',
		data : msg,
		success : function(data) {
			PopUpShow(data);
		},
		error : function(xhr, str) {
			alert('Возникла ошибка: ' + xhr.responseCode);
		}
	});
}