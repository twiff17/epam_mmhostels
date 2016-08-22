<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
	<title><fmt:message key="menu.administration" /></title>
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
            <%@include file="../header.jsp" %>
            <section id="content">
            	<%@include file="../search.jsp" %> 
                <article class="col2">
                	<h2><fmt:message key="menu.administration" /></h2>
                	<div class="pad">
	                	<div class="wrapper under">
	                        <figure class="left marg_right1"><img src="images/order.jpg" alt="" width="246" height="195"></figure>
	                        <p class="pad_bot2" style="font-size: large;">
	                            <fmt:message key="label.orders" />
	                        </p>
	                        <p class="pad_bot2">
	                            <fmt:message key="label.operations_order" /><br/>
	                        </p>
	                        <form action="Controller" method="post">
	                        	<input type="hidden" name="command" value="get_orders">
	                        	<input class="details-btn" style="margin-top: 100px;" type="submit" value="<fmt:message key="label.open" />">
	                        </form>
	                    </div>
	                    <div class="wrapper under">
	                        <figure class="left marg_right1"><img src="images/hotel.jpg" alt="" width="246" height="195"></figure>
	                        <p class="pad_bot2" style="font-size: large;">
	                            <fmt:message key="label.hostel_management" />
	                        </p>
	                        <p class="pad_bot2">
	                            <fmt:message key="label.operations_hostel" /><br/>
	                        </p>
	                        <form action="Controller" method="post">
	                        	<input type="hidden" name="command" value="get_hostels_admin">
	                        	<input class="details-btn" style="margin-top: 100px;" type="submit" value="<fmt:message key="label.open" />">
	                        </form>
	                    </div>
	                    <div class="wrapper under">
	                        <figure class="left marg_right1"><img src="images/room.jpg" alt="" width="246" height="195"></figure>
	                        <p class="pad_bot2" style="font-size: large;">
	                            <fmt:message key="label.room_management" />
	                        </p>
	                        <p class="pad_bot2">
	                            <fmt:message key="label.operations_room" /><br/>
	                        </p>
	                        <form action="Controller" method="post">
	                        	<input type="hidden" name="command" value="get_rooms_admin">
	                        	<input class="details-btn" style="margin-top: 100px;" type="submit" value="<fmt:message key="label.open" />">
	                        </form>
	                    </div>
	                    <div class="wrapper under">
	                        <figure class="left marg_right1"><img src="images/user.jpg" alt="" width="246" height="195"></figure>
	                        <p class="pad_bot2" style="font-size: large;">
	                            <fmt:message key="label.user_management" />
	                        </p>
	                        <p class="pad_bot2">
	                            <fmt:message key="label.operations_user" /><br/>
	                        </p>
	                        <form action="Controller" method="post">
	                        	<input type="hidden" name="command" value="get_users">
	                        	<input class="details-btn" style="margin-top: 100px;" type="submit" value="<fmt:message key="label.open" />">
	                        </form>
	                    </div>
                    </div>
                </article>
            </section>
        </div>
        <div class="block"></div>
    </div>
    <%@include file="../footer.jsp" %>
</body>
</html>