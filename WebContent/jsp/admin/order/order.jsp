<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
	<title><fmt:message key="menu.orders" /></title>
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
            <%@include file="../../header.jsp" %>
            <section id="content">
            	<%@include file="../../search.jsp" %> 
                <article class="col2">
                	<h2><fmt:message key="menu.orders" /></h2>
                	<c:forEach var="order" items="${orderList}">
	                	<div class="pad">
	                		<div class='wrapper under'>
	                       		<p class='pad_bot2'>
	                            	<strong>${order.country}, ${order.city}, ${order.hostelName}</strong> 
	                            </p>
	                            <p class='pad_bot2'>
	                            	<b><fmt:message key="label.in_date" />:</b><br/><i> ${order.inDate } </i> <br/>
	                            	<b><fmt:message key="label.in_date" />:</b><br/><i> ${order.inDate } </i> <br/>
	                                <b><fmt:message key="label.out_date" />:</b><br/><i> ${order.outDate } </i><br/>
	                                <b><fmt:message key="label.status" />:</b><br/><i> ${order.status} </i><br/>
	                                <b><fmt:message key="label.price" />:</b><br/> <i>${order.price } </i> <br/>
	                                <b><fmt:message key="label.order_date" />:</b><br/><i> ${order.orderTime} </i><br/>
	                            </p>
	                            <c:if test="${order.status eq 'В обработке'}">
	                                <input type="button" class="details-btn"  onClick="rejectOrder(${order.orderId})" value="<fmt:message key="label.reject_order" />">
	                            	<input type="button" class="details-btn"  onClick="acceptOrder(${order.orderId})" value="<fmt:message key="label.accept_order" />">
	                            </c:if>
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
					                        	<input type="hidden" name="command" value="get_orders">
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
    <%@include file="../../footer.jsp" %>
</body>
</html>