<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />

<html>
<head>
<title><fmt:message key="menu.users" /></title>
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
						<fmt:message key="menu.users" />
					</h2>
					<div class="table pad_left_top">
						<table>
							<tr>
								<td><fmt:message key="label.login" /></td>
								<td><fmt:message key="label.name" /></td>
								<td><fmt:message key="label.email" /></td>
								<td><fmt:message key="label.passport" /></td>
								<td><fmt:message key="label.phone" /></td>
								<td><fmt:message key="table.user.ban" /></td>
								<td><fmt:message key="table.user.discount" /></td>
								<td><fmt:message key="table.user.role" /></td>
								<td><fmt:message key="table.user.add_ban" /></td>
								<td><fmt:message key="table.user.add_discount" /></td>
							</tr>
							<c:forEach var="user" items="${userList}">
								<tr>
									<td>${user.login }</td>
									<td>${user.fullname }</td>
									<td>${user.email }</td>
									<td>${user.passport }</td>
									<td>${user.phone }</td>
									<td>${user.ban}</td>
									<td>${user.discount}</td>
									<td>${user.role}</td>
									<td><c:choose>
											<c:when test="${user.ban eq 'Да'}">
												<input type="button" class="icon-btn unban-btn"
													onClick="unBanUser(${user.userId})">
											</c:when>
											<c:otherwise>
												<input type="button" class="icon-btn ban-btn"
													onClick="banUser(${user.userId})">
											</c:otherwise>
										</c:choose></td>
									<td><c:if test="${user.discount eq 'Нет'}">
											<input type="button" class="icon-btn discount-btn"
												onClick="addDiscountUser(${user.userId})">
										</c:if></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</article>
			</section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="../../footer.jsp"%>
</body>
</html>