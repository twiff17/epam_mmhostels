<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />

<html>
<head>
<title><fmt:message key="label.rooms" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/ficon.gif" type="image/gif">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/slider.css">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/slider.js"></script>
<script type="text/javascript" src="js/tabs.js"></script>
</head>
<body>
	<div class="extra">
		<div class="main">
			<%@include file="header.jsp"%>
			<section id="content">
				<%@include file="search.jsp"%>
				<article class="col2">
					<h2>
						<fmt:message key="label.rooms_in_hostel" />
						${hostelName}
						<fmt:message key="label.on_date" />
						<br /> <br /> ${inDate} - ${outDate}
					</h2>
					<c:if test="${empty roomList }">
						<br />
						<h2>
							<fmt:message key="label.no_rooms" />
						</h2>
					</c:if>
					<c:if test="${not empty roomList }">
						<div class="table pad_left_top">
							<table>
								<tr>
									<td><fmt:message key="label.room_type" /></td>
									<td><fmt:message key="table.room.price" /></td>
									<td><fmt:message key="label.beds_number" /></td>
									<td><fmt:message key="label.book" /></td>
									<td><fmt:message key="table.room.fullpayment" /></td>
								</tr>
								<c:forEach var="room" items="${roomList}">
									<tr>
										<td>${room.roomType }</td>
										<td>${room.price}</td>
										<td>${room.bedsNumber }</td>
										<td><input type="button" class="icon-btn book-btn"
											onClick="bookRoom(${room.hostelId}, ${room.roomId }, '${inDate}','${outDate}', ${room.price}, 1)"></td>
										<td><input type="button" class="icon-btn fullpayment-btn"
											onClick="bookRoom(${room.hostelId}, ${room.roomId }, '${inDate}','${outDate}', ${room.price}, 0)"></td>
									</tr>
								</c:forEach>
							</table>
							<br />
							<c:if test="${discount eq true }">
								<b><fmt:message key="label.have_discount" /></b>
							</c:if>
						</div>
					</c:if>
				</article>
			</section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>