<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />

<html>
<head>
<title><c:choose>
		<c:when test="${not empty hostel}">
			<fmt:message key="menu.hostel_edit" />
		</c:when>
		<c:otherwise>
			<fmt:message key="menu.hostel_add" />
		</c:otherwise>
	</c:choose></title>
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
						<c:choose>
							<c:when test="${not empty hostel}">
								<fmt:message key="menu.hostel_edit" />
							</c:when>
							<c:otherwise>
								<fmt:message key="menu.hostel_add" />
							</c:otherwise>
						</c:choose>
					</h2>
					<c:choose>
						<c:when test="${not empty hostel}">
							<form id="hostel-form" action="Controller" method="post"
								class="form">
								<input type="hidden" name="command" value="edit_hostel">
								<input type="hidden" name="hostelId" value="${hostel.hostelId}">
						</c:when>
						<c:otherwise>
							<form id="hostel-form" action="Controller"
								enctype="multipart/form-data" method="post" class="form">
								<input type="hidden" name="command" value="add_hostel">
						</c:otherwise>
					</c:choose>
					<div class="wrapper">
						<fmt:message key="table.hostel.name" />
						(*): <input type="text" name="name" value="${hostel.name}"
							title="<fmt:message key="form_title.hostel_name" />" required
							pattern="[А-Я]{1}[А-Яа-я ]+" maxlength="30" class="input">
						<br />
					</div>

					<div class="wrapper">
						<fmt:message key="label.phone" />
						(*): <input type="tel" name="phone" value="${hostel.phone}"
							title="<fmt:message key="form_title.phone" />"
							pattern="^\(\d{3}\)\d{3}-\d{2}-\d{2}$" required class="input">
						<br />
					</div>

					<div class="wrapper">
						<fmt:message key="table.hostel.country" />
						(*): <select name="country" class="input">
							<c:forEach var="country" items="${countryList}">
								<c:choose>
									<c:when
										test="${not empty hostel and hostel.country eq country.name}">
										<option selected value="${country.name}">${country.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${country.name}">${country.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> <br />
					</div>

					<div class="wrapper">
						<fmt:message key="table.hostel.city" />
						(*): <input type="text" name="city" required
							title="<fmt:message key="form_title.hostel_name" />"
							pattern="[А-Я]{1}[А-Яа-я ]+" maxlength="30"
							value="${hostel.city}" class="input"> <br />
					</div>

					<div class="wrapper">
						<fmt:message key="label.address" />
						(*): <input type="text" name="address" required pattern=".{5,70}"
							title="<fmt:message key="form_title.address" />"
							value="${hostel.address}" class="input"> <br />
					</div>

					<div class="wrapper">
						<fmt:message key="table.hostel.currency" />
						(*): <select name="currency" class="input">
							<c:forEach var="currency" items="${currencyList}">
								<c:choose>
									<c:when
										test="${not empty hostel and hostel.currency eq currency.code}">
										<option selected value="${currency.code}">${currency.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${currency.code}">${currency.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select> <br />
					</div>
					<div class="wrapper">
						<fmt:message key="table.hostel.standart_price" />
						(*): <input type="text" name="standart_price" required
							title="<fmt:message key="form_title.integer" />"
							value="${hostel.standartPrice}" pattern="[1-9][0-9]*"
							class="input"> <br />
					</div>
					<div class="wrapper">
						<fmt:message key="table.hostel.description" />
						(*):
						<textarea name="description" required maxlength="300"
							class="input">${hostel.description}</textarea>
						<br />
					</div>

					<c:if test="${empty hostel}">
						<div class="wrapper">
							<fmt:message key="label.picture" />
							(*): <input name="file" type="file"
								accept="image/x-png, image/gif, image/jpeg" required> <br />
						</div>
					</c:if>

					<c:choose>
						<c:when test="${not empty hostel}">
							<input type="submit" class="button_submit"
								style="width: 140px; float: none;"
								value="<fmt:message key="table.edit" />">
						</c:when>
						<c:otherwise>
							<input type="submit" class="button_submit"
								style="width: 140px; float: none;"
								value="<fmt:message key="table.add" />">
						</c:otherwise>
					</c:choose>
					<br /> ${errorAddHostelMessage}
					</form>
				</article>
			</section>
		</div>
		<div class="block"></div>
	</div>
	<%@include file="../../footer.jsp"%>
</body>
</html>