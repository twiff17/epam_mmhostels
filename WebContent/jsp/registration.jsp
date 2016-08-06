<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
	<title><fmt:message key="title.registration"></fmt:message></title>
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
                <article class="col2 pad_left1" style="margin-top: 20px;">
                    <h2><fmt:message key="title.registration" /></h2>
                    <form method="post" action="Controller" class="form" id="registrationForm">
                        <input type="hidden" name="command" value="registration">
                        <div>
                            <div class="wrapper">
                                <fmt:message key="label.login" /> (*):
                                <input type="text" name="login" pattern="[A-Za-z0-9]{6,25}" required title="Логин только латинскими буквами и цифрами, длиной от 6 до 25 символов" class="input">
                                <br/>
                            </div>

                            <div class="wrapper">
                                <fmt:message key="label.password" /> (*):
                                <input type="password" name="password" required pattern="[A-Za-z0-9]{6,25}" title="Пароль только латинскими буквами и цифрами, длиной от 6 до 25 символов" 
                                class="input">
                                <br/>
                            </div>

                            <div class="wrapper">
                                <fmt:message key="label.name" /> (*):
                                <input type="text" name="fullname" required pattern="[А-Яа-я]+|[a-zA-z]+" title="В имени содержатся только буквы одного алфавита" maxlength="30" class="input">
                                <br/>
                            </div>

                            <div class="wrapper">
                                <fmt:message key="label.passport" /> (*):
                                <input type="text" name="passport" pattern="[A-Z]{2}[0-9]{7}" title="Введите номер паспорта в формате AA1111111" required maxlength="100" class="input">
                                <br/>
                            </div>

                            <div class="wrapper">
                                <fmt:message key="label.email" /> (*):
                                <input type="email" name="email" required pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" title="Введите корректный email" maxlength="30" class="input">
                                <br/>
                            </div>

                            <div class="wrapper">
                                <fmt:message key="label.phone" /> (*):
                                <input type="tel" name="phone" required pattern="^\(\d{3}\)\d{3}-\d{2}-\d{2}$" title="Введите телефон в формате: (КОД)XXX-XX-XX" class="input">
                                <br/>
                            </div>
                            <input type="submit" class="button_submit" style=" width: 120px; float:none; margin-left: 227px;" value="<fmt:message key="menu.registration" />">
                            <br/>
                            ${errorRegistrationMessage}
                            <br/>
                        </div>
                    </form>
                </article>
            </section>
        </div>
        <div class="block"></div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>