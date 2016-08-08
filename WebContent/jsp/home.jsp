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
									<strong>${hostel.country}, ${hostel.city}, ${hostel.name}</strong>
								</p>
								<p class='pad_bot2'>${hostel.description}</p>
								<a href="" class="right"><fmt:message key="label.book" /></a>
							</div>
						</div>
					</c:forEach>
					<div class="right">
						<%--For displaying Previous link except for the 1st page --%>
					    <c:if test="${currentPage != 1}">
					        <td><a href="Controller?command=get_page&page=home&pageNumber=${currentPage - 1}">Previous</a></td>
					    </c:if>
					 
					    <%--For displaying Page numbers. 
					    The when condition does not display a link for the current page--%>
					    <table border="1" cellpadding="5" cellspacing="5">
					        <tr>
					            <c:forEach begin="1" end="${noOfPages}" var="i">
					                <c:choose>
					                    <c:when test="${currentPage eq i}">
					                        <td>${i}</td>
					                    </c:when>
					                    <c:otherwise>
					                        <td><a href="Controller?command=get_page&page=home&pageNumber=${i}">${i}</a></td>
					                    </c:otherwise>
					                </c:choose>
					            </c:forEach>
					        </tr>
					    </table>
					     
					    <%--For displaying Next link --%>
					    <c:if test="${currentPage lt noOfPages}">
					        <td><a href="Controller?command=get_page&page=home&pageNumber=${currentPage + 1}">Next</a></td>
					    </c:if>
				    </div>
				</article>
			 </section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="footer.jsp" %>
</body>
</html>