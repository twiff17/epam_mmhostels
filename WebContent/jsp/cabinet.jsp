<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="by.epam.hostelbeta.util.LocalDateCompareUtil"%>
<!DOCTYPE html>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />

<html>
<head>
<title><fmt:message key="menu.cabinet" /></title>
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
						<fmt:message key="label.your_orders" />
					</h2>
					<c:if test="${empty orderList}">
						<br />
						<h2>
							<fmt:message key="label.no_orders" />
						</h2>
					</c:if>
					<c:if test="${not empty orderList }">
						<div class="table pad_left_top">
							<table>
								<tr>
									<td><fmt:message key="label.city" /></td>
									<td><fmt:message key="table.hostel.name" /></td>
									<td><fmt:message key="table.room.number" /></td>
									<td><fmt:message key="label.price" /></td>
									<td><fmt:message key="label.in_date" /></td>
									<td><fmt:message key="label.out_date" /></td>
									<td><fmt:message key="label.status" /></td>
									<td><fmt:message key="label.order_date" /></td>
									<td><fmt:message key="label.booking" /></td>


									<td><fmt:message key="label.cancel_order" /></td>
								</tr>
								<c:forEach var="order" items="${orderList}">
									<tr>
										<td>${order.city}</td>
										<td>${order.hostelName }</td>
										<td>${order.roomId }</td>
										<td>${order.price }</td>
										<td>${order.inDate }</td>
										<td>${order.outDate}</td>
										<td>${order.status}</td>
										<td>${order.orderTime}</td>
										<c:if test="${order.booking }">
											<td><fmt:message key="label.yes" /></td>
										</c:if>
										<c:if test="${!order.booking }">
											<td><fmt:message key="label.no" /></td>
										</c:if>
										<td><c:if
												test="${(order.status eq 'В обработке' or order.status eq 'Принят') and !LocalDateCompareUtil.isBeforeOrEqualsCurrentDate(order.getInDate())}">
												<input type="button" class="icon-btn cancel-btn"
													onClick="cancelOrder(${order.orderId})" value="">
											</c:if></td>
									</tr>
								</c:forEach>
							</table>
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