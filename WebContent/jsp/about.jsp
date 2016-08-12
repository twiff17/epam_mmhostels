<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
	<title><fmt:message key="menu.about" /></title>
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
            <%@include file="header.jsp" %>
            <section id="content">
            	<%@include file="search.jsp" %> 
                <article class="col2">
                	<h2><fmt:message key="label.contacts" /></h2>
                	<div class="pad">
                		<div class="about">
                    		<p>
                    			<b><fmt:message key="label.our_address" />:</b><br/> г. Минск,<br/> ул. Беды, 4,<br/> к.1510.
                    		</p>
                    		<p>
    	                        <b><fmt:message key="label.our_phone" />:</b><br/> +375(33) 312-9249
    	                    </p>
    	                    <p>
                                <b><fmt:message key="label.our_email" />:</b><br/> dusenok.boris@mail.ru
                            </p>
	                    </div>
	                	<h2><fmt:message key="label.map" /></h2>
			            <div class="wrapper">
			                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d4698.324768232503!2d27.5874745590678!3d53.92885889551246!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sru!2sru!4v1402170774347" width="600" height="450" style="border:0; padding-left: 74px; padding-top: 3px"></iframe>
			            </div>
			        </div>
                </article>
            </section>
        </div>
        <div class="block"></div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>