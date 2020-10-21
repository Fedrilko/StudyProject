<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
</head>
<body>
<div>
<form action="login" method="POST" id="login_form">
<div>
<label>Login:</label>
<input type="text" name="login" id="login" required/>
<label id="login_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<label>Password:</label>
<input type="password" name="password" id="password" required/>
<label id="password_msg" style="display:none;">Field can not be empty</label>
</div>
<div>
<input type="submit" value="Sign in" id="login_submit"/>
</div>
</form>
</div>
<c:if test="${msg != null}">
<p>${msg}</p>
</c:if>

<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/login_form_validation_script.js" />"></script>
</body>
</html>
