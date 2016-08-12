<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
	<title><fmt:message key="menu.login"></fmt:message></title>
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
                <article class="col2 pad_left1" style="margin-top: 20px;">
                    <h2><fmt:message key="menu.login"></fmt:message></h2>
                    <form method="post" action="Controller" class="form" id="loginForm">
                    	<input type="hidden" name="command" value="login">
                        <div>
                            <div class="wrapper">
                                <fmt:message key="label.login"></fmt:message>:
                                 <input type="text" id="loginInput" name="login" pattern="[A-Za-z0-9]{4,25}" required title="<fmt:message key="label.login_title"></fmt:message>" placeholder="<fmt:message key="label.login"></fmt:message>" class="input"><br/>
                            </div>

                            <div class="wrapper">
                                    <fmt:message key="label.password"></fmt:message>:
                                <input type="password" name="password" pattern="[A-Za-z0-9]{4,25}" title="<fmt:message key="label.pass_title"></fmt:message>" required placeholder="<fmt:message key="label.password"></fmt:message>" class="input">
                                    <br/>
                            </div>
                            <input type="submit" class="button_submit" style="float:none; margin-left: 260px;" value="<fmt:message key="menu.login"></fmt:message>">
                            <br/>
                            ${errorLoginPassMessage }
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