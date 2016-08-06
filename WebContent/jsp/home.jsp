<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
	<title><fmt:message key="menu.main" /></title>
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
</head>
<body>
	<div class="extra">
		<div class="main">
			<%@ include file="header.jsp"%>
			<section id="content"> 
			<%@include file="search.jsp" %> 
				<article class="col2">
					<h2><fmt:message key="label.popular" /></h2>
					<c:forEach var="hostel" items="${hostelList}">
						<div class='pad'>
							<div class='wrapper under'>
								<figure class='left marg_right1'>
								<img src='images/hostels/1.png' alt='' width='300' height='150'></figure>
								<p class='pad_bot2'>
									<strong>Россия, Санкт-Петербург, Милениум</strong>
								</p>
								<p class='pad_bot2'>Еда, старый город и культура сделают вашу
									поездку сюда незабываемой!</p>
								<a href="" class="right"><fmt:message key="label.book" /></a>
							</div>
						</div>
					</c:forEach>
				</article>
			 </section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>