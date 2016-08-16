<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="error.error_page" /></title>
</head>
<body>
	<fmt:message key="error.sorry" /><br/>
	<b><fmt:message key="error.error_details" />:</b> <br/>
	<fmt:message key="error.request_from" />: ${pageContext.errorData.requestURI}
	<br/>
	<fmt:message key="error.servlet_name" />: ${pageContext.errorData.servletName}
	<br/>
	<fmt:message key="error.status_code" />: ${pageContext.errorData.statusCode}
	<br/>
	<fmt:message key="error.exception" />: ${pageContext.errorData.throwable}
</body>
</html>