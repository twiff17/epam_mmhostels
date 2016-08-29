<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="pagecontent" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="error.no_access" /></title>
</head>
<body>
	<fmt:message key="error.no_access_message" />
</body>
</html>