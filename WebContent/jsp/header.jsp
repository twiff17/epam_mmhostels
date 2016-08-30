<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<!DOCTYPE html>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />
<header>
	<div class="wrapper">
		<h1>
			<img src="images/logo.png">
		</h1>
		<div class="right">
			<div class="lang_menu">
				<input class="locale-btn-ru" type="button"
					onClick="changeLocale('Default')" value="" /> <input
					class="locale-btn-en" type="button" onClick="changeLocale('en_US')"
					value="">
			</div>
			<c:choose>
				<c:when test="${empty sessionUser}">
					<nav>
						<ul id="top_nav">
							<c:choose>
								<c:when test="${page eq 'login'}">
									<li>
										<form method="post" action="Controller">
											<input type="hidden" name="command" value="get_page" /> <input
												type="hidden" name="page" value="login"> <input
												class="lk active" type="submit"
												value="<fmt:message key="menu.login" />" />
										</form>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<form method="post" action="Controller">
											<input type="hidden" name="command" value="get_page" /> <input
												type="hidden" name="page" value="login"> <input
												class="lk" type="submit"
												value="<fmt:message key="menu.login" />" />
										</form>
									</li>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${page eq 'registration'}">
									<li>
										<form method="post" action="Controller">
											<input type="hidden" name="command" value="get_page" /> <input
												type="hidden" name="page" value="registration"> <input
												class="lk active" type="submit"
												value="<fmt:message key="menu.registration" />" />
										</form>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<form method="post" action="Controller">
											<input type="hidden" name="command" value="get_page" /> <input
												type="hidden" name="page" value="registration"> <input
												class="lk" type="submit"
												value="<fmt:message key="menu.registration" />" />
										</form>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</c:when>
				<c:otherwise>
					<div class="user_data">
						<ctg:user-data />
					</div>
					<nav>
						<ul id="top_nav">

							<li>
								<form method="post" action="Controller">
									<input type="hidden" name="command" value="logout" /> <input
										type="hidden" name="page" value="home"> <input
										class="lk" type="submit"
										value="<fmt:message key="menu.logout" />" />
								</form>
							</li>
						</ul>
					</nav>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<nav>
		<ul id="menu">
			<c:choose>
				<c:when test="${page eq 'home' or empty page}">
					<li>
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_home" /> <input
								class="menu_lk active" type="submit"
								value="<fmt:message key="menu.main" />" />
						</form>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_home" /> <input
								class="menu_lk" type="submit"
								value="<fmt:message key="menu.main" />" />
						</form>
					</li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${page eq 'hostels'}">
					<li>
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_hostels" /> <input
								class="menu_lk active" type="submit"
								value="<fmt:message key="menu.hostels" />" />
						</form>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_hostels" /> <input
								class="menu_lk" type="submit"
								value="<fmt:message key="menu.hostels" />" />
						</form>
					</li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when
					test="${not empty sessionUser and sessionUser.role eq 'client'}">
					<c:choose>
						<c:when test="${page eq 'cabinet'}">
							<li>
								<form method="post" action="Controller">
									<input type="hidden" name="command" value="get_cabinet" /> <input
										type="hidden" name="userId" value="${sessionUser.userId}">
									<input class="menu_lk active" type="submit"
										value="<fmt:message key="menu.cabinet" />" />
								</form>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<form method="post" action="Controller">
									<input type="hidden" name="command" value="get_cabinet" /> <input
										type="hidden" name="userId" value="${sessionUser.userId}">
									<input class="menu_lk" type="submit"
										value="<fmt:message key="menu.cabinet" />" />
								</form>
							</li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when
					test="${not empty sessionUser and sessionUser.role eq 'admin'}">
					<c:choose>
						<c:when test="${page eq 'admin'}">
							<li>
								<form method="post" action="Controller">
									<input type="hidden" name="command" value="get_page" /> <input
										type="hidden" name="page" value="admin"> <input
										class="menu_lk active" type="submit"
										value="<fmt:message key="menu.admin" />" />
								</form>
							</li>
						</c:when>
						<c:otherwise>
							<li>
								<form method="post" action="Controller">
									<input type="hidden" name="command" value="get_page" /> <input
										type="hidden" name="page" value="admin"> <input
										class="menu_lk" type="submit"
										value="<fmt:message key="menu.admin" />" />
								</form>
							</li>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<li><input class="menu_lk" type="button"
						value="<fmt:message key="menu.cabinet" />"
						onclick="PopUpShow('<fmt:message key="menu.login_required" />')" />
					</li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${page eq 'about'}">
					<li class="end">
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_page" /> <input
								type="hidden" name="page" value="about"> <input
								class="menu_lk active" type="submit"
								value="<fmt:message key="menu.about" />" />
						</form>
					</li>
				</c:when>
				<c:otherwise>
					<li class="end">
						<form method="post" action="Controller">
							<input type="hidden" name="command" value="get_page" /> <input
								type="hidden" name="page" value="about"> <input
								class="menu_lk" type="submit"
								value="<fmt:message key="menu.about" />" />
						</form>
					</li>
				</c:otherwise>
			</c:choose>
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