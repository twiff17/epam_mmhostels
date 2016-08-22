<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />

<html>
<head>
<title><c:choose>
		<c:when test="${not empty room}">
			<fmt:message key="menu.room_edit" />
		</c:when>
		<c:otherwise>
			<fmt:message key="menu.room_add" />
		</c:otherwise>
	</c:choose></title>
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
						<c:choose>
							<c:when test="${not empty room}">
								<fmt:message key="menu.room_edit" />
							</c:when>
							<c:otherwise>
								<fmt:message key="menu.room_add" />
							</c:otherwise>
						</c:choose>
					</h2>
					<form id="room-form" action="Controller" method="post" class="form">
						<c:choose>
							<c:when test="${not empty room}">
								<input type="hidden" name="command" value="edit_room">
								<input type="hidden" name="hostelId" value="${room.hostelId}">
								<input type="hidden" name="roomId" value="${room.roomId}">
							</c:when>
							<c:otherwise>
								<input type="hidden" name="command" value="add_room">
							</c:otherwise>
						</c:choose>

						<div class="wrapper">
							<fmt:message key="table.room.hostel" />
							(*):
							<c:choose>
								<c:when test="${empty room}">
									<select name="hostelId" class="input">
								</c:when>
								<c:otherwise>
									<select disabled name="hostelId" class="input">
								</c:otherwise>
							</c:choose>

							<c:forEach var="hostel" items="${hostelList}">
								<c:choose>
									<c:when
										test="${not empty room and room.hostelId eq hostel.hostelId}">
										<option selected value="${hostel.hostelId}">${hostel.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${hostel.hostelId}">${hostel.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							</select> <br />
						</div>

						<div class="wrapper">
							<fmt:message key="table.room.number" />
							(*):
							<c:choose>
								<c:when test="${empty room}">
									<input type="text" name="roomId"
										title="<fmt:message key="form_title.integer" />" required
										pattern="[1-9][0-9]*" maxlength="30" class="input">
								</c:when>
								<c:otherwise>
									<input type="text" disabled name="roomId"
										value="${room.roomId}" required maxlength="30" class="input">
								</c:otherwise>
							</c:choose>
							<br />
						</div>

						<div class="wrapper">
							<fmt:message key="table.room.type" />
							(*): <select name="roomType" class="input">
								<c:forEach var="type" items="${roomTypeList}">
									<c:choose>
										<c:when
											test="${not empty room and room.roomType eq type.roomTypeId}">
											<option selected value="${type.roomTypeId}">${type.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${type.roomTypeId}">${type.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select> <br />
						</div>

						<div class="wrapper">
							<fmt:message key="table.room.beds_number" />
							(*): <input type="text" name="bedsNumber" required
								title="<fmt:message key="form_title.integer" />"
								value="${room.bedsNumber}" pattern="[1-9][0-9]*" class="input">
							<br />
						</div>

						<c:choose>
							<c:when test="${not empty room}">
								<input type="submit" class="button_submit"
									style="width: 140px; float: none;"
									value="<fmt:message key="table.edit" />">
							</c:when>
							<c:otherwise>
								<input type="submit" class="button_submit"
									style="width: 140px; float: none;"
									value="<fmt:message key="table.add" />">
							</c:otherwise>
						</c:choose>
						<br /> ${errorAddRoomMessage}
					</form>
				</article>
			</section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="../../footer.jsp"%>
</body>
</html>