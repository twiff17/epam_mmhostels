<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />

<html>
<head>
<title><fmt:message key="label.hostels" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="images/ficon.gif" type="image/gif">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/slider.css">
<link rel="stylesheet" type="text/css" href="css/layout.css">
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
<script type="text/javascript" src="js/datepicker.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/slider.js"></script>
<script type="text/javascript" src="js/tabs.js"></script>
<script type="text/javascript" src="js/time.js"></script>
</head>
<body>
	<div class="extra">
		<div class="main">
			<%@include file="header.jsp"%>
			<section id="content">
				<%@include file="search.jsp"%>
				<article class="col2">
					<h2>
						<fmt:message key="label.our_hostels" />
					</h2>
					<c:forEach var="hostel" items="${hostelList}">
						<div class='pad'>
							<div class='wrapper under'>
								<figure class='left marg_right1'>
									<img src='images/hostels/${hostel.imageName}' alt=''
										width='300' height='150'>
								</figure>
								<p class='pad_bot2'>
									<strong>${hostel.country}, ${hostel.city},
										${hostel.name}</strong>
								</p>
								<p class='pad_bot2'>
									<b><fmt:message key="label.room_types" /></b><br /> <i>${hostel.roomTypes }</i>
									<br /> <b><fmt:message key="label.price" /></b><br /> <i>${hostel.minPrice }<c:if
											test="${hostel.minPrice ne hostel.maxPrice}">-${hostel.maxPrice}</c:if>
										${hostel.currency}
									</i> <br /> <b><fmt:message key="label.phone" /></b><br /> <i>
										${hostel.phone}</i> <br /> <b><fmt:message
											key="label.address" /></b><br /> <i> ${hostel.address}</i> <br />
									<br> <i> ${hostel.description }</i> <br />
								</p>
								<c:choose>
									<c:when test="${role eq 'client' and ban eq 'false'}">
										<input type="button" class="details-btn"
											onClick="PopUpTwoShow(${hostel.hostelId},'${hostel.name }')"
											value="<fmt:message key="label.book" />">
									</c:when>
									<c:when test="${role eq 'client' and ban eq 'true'}">
										<input class="details-btn" type="button"
											value="<fmt:message key="label.book" />"
											onClick="PopUpShow('<fmt:message key="label.you_banned" />')">
									</c:when>
									<c:when test="${role eq 'admin'}">

									</c:when>
									<c:otherwise>
										<input class="details-btn" type="button"
											value="<fmt:message key="label.book" />"
											onClick="PopUpShow('<fmt:message key="menu.login_required" />')">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
					<div class="right">
						<table>
							<tr>
								<c:forEach begin="1" end="${noOfPages}" var="i">
									<c:choose>
										<c:when test="${currentPage eq i}">
											<input disabled class="page_nav_button active" value="${i}">
										</c:when>
										<c:otherwise>
											<form method="post" action="Controller">
												<input type="hidden" name="command" value="get_hostels">
												<input type="hidden" name="pageNumber" value="${i}">
												<input class="page_nav_button" type="submit" value="${i}">
											</form>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</tr>
						</table>
					</div>
				</article>
			</section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="footer.jsp"%>
	<div class="b-popup" id="popup2" style="z-index: 10">
		<div class="b-popup-content">
			<span><fmt:message key="label.book_dates" /></span> <br>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="get_free_rooms">

				<input type="hidden" id="hostelId" name="hostelId"> <input
					type="hidden" id="hostelName" name="hostelName">
				<fmt:message key="label.in_date" />
				(*) <input type="date" class="marg1" required id="inDate" name="inDate">
				<br />
				<fmt:message key="label.out_date" />
				(*) <input type="date" class="marg1" required id="outDate" name="outDate">
				<br /> <input type="submit" class="details-btn"
					value="<fmt:message
					key="label.send" />"> <br />
			</form>
			<br> <a href="javascript:PopUpTwoHide(true)"><fmt:message
					key="menu.close_window" /></a>
		</div>
	</div>
	<c:if test="${not empty errorMessage}">>
	<div class="b-popup" id="error_popup" style="z-index: 10">
			<div class="b-popup-content">
				<span id="message">${errorMessage}</span> <br> <br> <a
					href="javascript:ErrorPopUpHide(false)"><fmt:message
						key="menu.close_window" /></a>
			</div>
		</div>
	</c:if>
</body>
</html>

