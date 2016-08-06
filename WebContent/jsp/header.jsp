<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<header>
	<div class="wrapper">
		<h1>
			<a href="home.html" id="logo"><img src="images/logo.png"></a>
		</h1>
		<div class="right">
			<nav>
				<ul id="top_nav">
					<li>
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_page" />
							<input type="hidden" name="page" value="login">
							<input class="lk" type="submit" value="<fmt:message key="menu.login" />" />
						</form>
					</li>
					<li>
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_page" />
							<input type="hidden" name="page" value="registration">
							<input class="lk" type="submit" value="<fmt:message key="menu.registration" />" />
						</form>
					</li>
				</ul>
			</nav>
		</div>
	</div>
	<nav>
		<ul id="menu">
			<li>
				<form method="post" action="Controller">
					<input type="hidden" name="command" value="get_page" />
					<input type="hidden" name="page" value="home">
					<input class="menu_lk" type="submit" value="<fmt:message key="menu.main" />" />
				</form>
			</li>
			<li>
				<form method="post" action="Controller">
					<input type="hidden" name="command" value="get_page" />
					<input type="hidden" name="page" value="hostels">
					<input class="menu_lk" type="submit" value="<fmt:message key="menu.hostels" />" />
				</form>
			</li>
			<li>
				<form method="post" action="Controller">
					<input type="hidden" name="command" value="get_page" />
					<input type="hidden" name="page" value="cabinet">
					<input class="menu_lk" type="submit" value="<fmt:message key="menu.cabinet" />" />
				</form>
			</li>
			<li class="end">
				<form method="post" action="Controller">
					<input type="hidden" name="command" value="get_page" />
					<input type="hidden" name="page" value="about">
					<input class="menu_lk" type="submit" value="<fmt:message key="menu.about" />" />
				</form>
			</li>
		</ul>
	</nav>
	<div class="slider">
		<ul>
			<li><img src="images/slider/4.jpg" alt=""></li>
			<li><img src="images/slider/1.jpg" alt=""></li>
			<li><img src="images/slider/2.jpg" alt=""></li>
			<li><img src="images/slider/3.jpg" alt=""></li>
		</ul>
	</div>
</header>