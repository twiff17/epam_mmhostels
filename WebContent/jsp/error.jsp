<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
	Request from ${pageContext.errorData.requestURI} is failed
	<br/>
	Servlet name or type: ${pageContext.errorData.servletName}
	<br/>
	Status code: ${pageContext.errorData.statusCode}
	<br/>
	Exception: ${pageContext.errorData.throwable}
	<br/>
	Error: ${errorStackTrace}
</body>
</html>