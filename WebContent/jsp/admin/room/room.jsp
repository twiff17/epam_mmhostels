<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<%@include file="../../header.jsp"%>
			<section id="content">
				<%@include file="../../search.jsp"%>
				<article class="col2">
					<h2>
						<fmt:message key="label.rooms" />
					</h2>
					<div class="table pad_left_top">
						<table>
							<tr>
								<td><fmt:message key="table.hostel.name" /></td>
								<td><fmt:message key="table.room.number" /></td>
								<td><fmt:message key="table.room.type" /></td>
								<td><fmt:message key="table.room.beds_number" /></td>
								<td><fmt:message key="table.room.price" /></td>
								<td><fmt:message key="table.delete" /></td>
								<td><fmt:message key="table.edit" /></td>
							</tr>
							<c:forEach var="room" items="${roomList}">
								<tr>
									<td><b>${room.hostelName }</b></td>
									<td>${room.roomId }</td>
									<td>${room.roomType }</td>
									<td>${room.bedsNumber }</td>
									<td>${room.price }</td>
									<td><input type="button" class="icon-btn delete-btn"
										onClick="deleteRoom(${room.hostelId},${room.roomId})"></td>
									<td>
										<form action="Controller" method="post">
											<input type="hidden" name="command" value="get_room_edit">
											<input type="hidden" name="hostelId" value="${room.hostelId}">
											<input type="hidden" name="roomId" value="${room.roomId}">
											<input type="submit" class="icon-btn edit-btn" value="">
										</form>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="get_room_add">
						<input class="details-btn" style="margin-top: 28px;" type="submit"
							value="<fmt:message key="table.add" />">
					</form>
				</article>
			</section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="../../footer.jsp"%>
</body>
</html>