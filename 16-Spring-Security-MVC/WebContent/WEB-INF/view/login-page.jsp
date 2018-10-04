<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Custom Login Page</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h5>Custom Login Page</h5>
	<hr>
	<form:form action="confirmLogin" method="POST">
		<c:if test="${param.error!=null}">
			<i class="error">Sorry! Wrong Credentials.</i>
			<br><br>
		</c:if>
		<c:if test="${param.logout!=null}">
			<i class="error">You have been logged out.</i>
			<br><br>
		</c:if>
	Username: <input type="text" name="username" />
		<br>
		<br>
	Password: <input type="password" name="password" />
		<br>
		<br>
		<input type="submit" value="Login" />
	</form:form>
</body>
</html>