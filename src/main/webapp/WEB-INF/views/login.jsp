<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
</head>
<body>
<form action="login" method="POST">
<label>Login:</label>
<input type="text" name="login" id="login"/>
<label>Password:</label>
<input type="password" name="password" id="password"/>
<input type="submit" value="Sign in"/>
</form>
<c:if test="${msg != null}">
<p>${msg}</p>
</c:if>
</body>
</html>
